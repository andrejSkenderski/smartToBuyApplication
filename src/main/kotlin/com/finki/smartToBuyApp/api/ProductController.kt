package com.finki.smartToBuyApp.api

import com.finki.smartToBuyApp.api.request.ProductRequest
import com.finki.smartToBuyApp.domain.Product
import com.finki.smartToBuyApp.domain.dto.ProductDto
import com.finki.smartToBuyApp.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/api/products")
class ProductController(
        private val service: ProductService
) {
    @PostMapping
    fun save(@RequestBody request: ProductRequest): Product = service.save(request)

    @PutMapping("/{id}/update")
    fun update(@PathVariable id: Long, @RequestBody request: ProductRequest): Product =
            service.update(id = id, request = request)

    @GetMapping
    fun findAll() = service.findAll()
            .map { ProductDto(id = it.id, name = it.name, price = it.price, image = it.image, description = it.description) }
            .toList()
}