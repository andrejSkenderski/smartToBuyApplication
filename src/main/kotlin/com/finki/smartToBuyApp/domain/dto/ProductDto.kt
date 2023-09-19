package com.finki.smartToBuyApp.domain.dto

data class ProductDto(
    val id: Long,
    val name: String,
    val price: Double,
    val description: String?,
    val image: String?
)