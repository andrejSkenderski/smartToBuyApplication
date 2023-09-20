package com.finki.smartToBuyApp.service

import com.finki.smartToBuyApp.domain.CartItem
import com.finki.smartToBuyApp.domain.CartItemStatus
import com.finki.smartToBuyApp.domain.Product
import com.finki.smartToBuyApp.domain.User
import com.finki.smartToBuyApp.repository.CartItemRepository
import com.finki.smartToBuyApp.repository.ProductRepository
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.FileInputStream
import java.io.FileOutputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.persistence.EntityNotFoundException

@Service
class CartService(
    private val cartItemRepository: CartItemRepository,
    private val productRepository: ProductRepository,
    private val userService: UserService,
) {

    @Transactional
    fun addToCart(productId: Long, status: CartItemStatus = CartItemStatus.ACTIVE) {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val username = userDetails.username // Get the username of the currently logged-in user
        val user = userService.getUserByEmail(username) // Implement the user service to retrieve a user by username

        val product = productRepository.findById(productId)
            .orElseThrow { EntityNotFoundException("Product not found with ID: $productId") }

        // Check if the product is already in the user's cart
        val activeCartItem = cartItemRepository.findByUserAndStatus(user!!, CartItemStatus.ACTIVE)


        if (activeCartItem != null) {
            val productsList = activeCartItem.products.toMutableList()
            productsList.add(product)
            activeCartItem.products = productsList
            cartItemRepository.save(activeCartItem)
        } else {
            // Create a new cart item if there's no active cart item for the user
            val cartItem = CartItem(0, user, mutableListOf(product), status)
            cartItemRepository.save(cartItem)
        }
    }

    @Transactional
    fun removeFromCart(productId: Long) {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val username = userDetails.username // Get the username of the currently logged-in user
        val user = userService.getUserByEmail(username) // Implement the user service to retrieve a user by username

        val product = productRepository.findById(productId)
            .orElseThrow { EntityNotFoundException("Product not found with ID: $productId") }

        // Check if the product is already in the user's cart
        val activeCartItem = cartItemRepository.findByUserAndStatus(user!!, CartItemStatus.ACTIVE)!!
        val productList = activeCartItem.products.toMutableList()
        productList.remove(product)
        activeCartItem.products = productList
        cartItemRepository.save(activeCartItem)
    }


    @Transactional(readOnly = true)
    fun getCartItems(): CartItem {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val username = userDetails.username // Get the username of the currently logged-in user
        val user = userService.getUserByEmail(username) // Implement the user service to retrieve a user by username

        // Retrieve the user's cart items
        return cartItemRepository.findByUserAndStatus(user!!, CartItemStatus.ACTIVE)
            ?: throw RuntimeException("There are not items in cart")
    }


    @Transactional
    fun purchase(): Double {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val username = userDetails.username // Get the username of the currently logged-in user
        val user = userService.getUserByEmail(username) // Implement the user service to retrieve a user by username

        // Retrieve the user's active cart item
        val activeCartItem = cartItemRepository.findByUserAndStatus(user!!, CartItemStatus.ACTIVE)

        if (activeCartItem != null) {
            val cartItems = activeCartItem.products

            // Calculate the total price of the purchased items\
            val totalPrice = cartItems.sumByDouble { it.price }

            // Update the cart status to PURCHASED
            activeCartItem.status = CartItemStatus.PURCHASED
            cartItemRepository.save(activeCartItem)

            updateExcelFileWithPurchasedProducts(user, cartItems)

            // You can perform additional logic here, such as processing payment, updating product quantities, etc.

            return totalPrice
        } else {
            // Handle the case where there is no active cart
            throw EntityNotFoundException("No active cart found for the user.")
        }
    }

    private fun updateExcelFileWithPurchasedProducts(user: User, products: List<Product>) {
        val excelFilePath = "D:\\Andrej\\smartToBuyApp-model\\groceries_data.xlsx"

        // Load the existing workbook
        val fileInputStream = FileInputStream(excelFilePath)
        val workbook = XSSFWorkbook(fileInputStream)

        // Get the first sheet (you can modify this to work with other sheets)
        val sheet = workbook.getSheetAt(0)

        products.forEach { product ->
            // Create a new row
            val newRow = sheet.createRow(sheet.lastRowNum + 1)
            // Add data to the new row
            val user_id_cell = newRow.createCell(0, CellType.NUMERIC)
            user_id_cell.setCellValue(user.id.toDouble())

            val item_id_cell = newRow.createCell(1, CellType.NUMERIC)
            item_id_cell.setCellValue(product.id.toDouble())

            val date_cell = newRow.createCell(2, CellType.STRING)
            date_cell.setCellValue(LocalDate.now().format(DateTimeFormatter.ISO_DATE))

            val product_name_cell = newRow.createCell(3, CellType.STRING)
            product_name_cell.setCellValue(product.name)
        }

        // Save the changes
        fileInputStream.close()
        val fileOutputStream = FileOutputStream(excelFilePath)
        workbook.write(fileOutputStream)
        fileOutputStream.close()

        // Close the workbook
        workbook.close()

        println("Excel file updated successfully with purchased items.")
    }
}
