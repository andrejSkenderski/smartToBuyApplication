package com.finki.smartToBuyApp.service

import com.finki.smartToBuyApp.api.request.ProductRequest
import com.finki.smartToBuyApp.domain.Product
import com.finki.smartToBuyApp.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ProductService(
    private val repository: ProductRepository,
    private val categoryService: ProductCategoryService
) {
    fun save(request: ProductRequest): Product {
        val category = categoryService.findById(request.categoryId)
        val newProduct = Product(
            name = request.name,
            description = request.description,
            price = request.price,
            category = category
        )
        return repository.save(newProduct)
    }

    fun findById(id: Long): Product =
        repository.findByIdOrNull(id) ?: throw EntityNotFoundException("No Product found with ID: $id")

    fun update(id: Long, request: ProductRequest): Product {
        val product = findById(id)
        val category = categoryService.findById(request.categoryId)
        return repository.save(
            product.copy(
                id = id,
                name = request.name,
                description = request.description,
                price = request.price,
                category = category
            )
        )
    }

    fun findAll(): List<Product> = repository.findAll()


}