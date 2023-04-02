package com.finki.smartToBuyApp.api

import com.finki.smartToBuyApp.api.request.ProductCategoryRequest
import com.finki.smartToBuyApp.domain.ProductCategory
import com.finki.smartToBuyApp.service.ProductCategoryService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/api/product-category")
class ProductCategoryController(
    private val service: ProductCategoryService
) {
    @PostMapping
    fun save(@RequestBody request: ProductCategoryRequest): ProductCategory = service.save(request)

    @GetMapping
    fun findAll() = service.findAll()
}