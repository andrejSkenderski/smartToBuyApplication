package com.finki.smartToBuyApp.api

import com.finki.smartToBuyApp.api.response.ProductResponse
import com.finki.smartToBuyApp.api.response.toResponse
import com.finki.smartToBuyApp.service.SuggestedProductService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/api/suggested-products")
class SuggestedProductController(
    private val service: SuggestedProductService
) {
    @GetMapping
    fun getSuggestedProductsForActiveUser(): List<ProductResponse> =
        service.getSuggestedProductsForActiveUser().map { it.toResponse() }

}