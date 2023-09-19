package com.finki.smartToBuyApp.service

import com.finki.smartToBuyApp.domain.CartItem
import com.finki.smartToBuyApp.domain.CartItemStatus
import com.finki.smartToBuyApp.repository.CartItemRepository
import com.finki.smartToBuyApp.repository.ProductRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
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
            // Calculate the total price of the purchased items
            val totalPrice = activeCartItem.products.sumByDouble { it.price }

            // Update the cart status to PURCHASED
            activeCartItem.status = CartItemStatus.PURCHASED
            cartItemRepository.save(activeCartItem)

            // You can perform additional logic here, such as processing payment, updating product quantities, etc.

            return totalPrice
        } else {
            // Handle the case where there is no active cart
            throw EntityNotFoundException("No active cart found for the user.")
        }
    }
}
