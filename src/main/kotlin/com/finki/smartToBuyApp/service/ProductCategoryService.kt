package com.finki.smartToBuyApp.service

import com.finki.smartToBuyApp.api.request.ProductCategoryRequest
import com.finki.smartToBuyApp.domain.ProductCategory
import com.finki.smartToBuyApp.repository.ProductCategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ProductCategoryService(
    private val repository: ProductCategoryRepository
) {
    fun findById(id: Long): ProductCategory =
        repository.findByIdOrNull(id) ?: throw EntityNotFoundException("No category found with ID: $id")

    fun save(request: ProductCategoryRequest): ProductCategory =
        repository.save(ProductCategory(name = request.name))

    fun findAll(): List<ProductCategory> = repository.findAll()
}