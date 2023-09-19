package com.finki.smartToBuyApp.api.request

data class ProductRequest(
    val name: String,
    val description: String?,
    val price: Double,
    val category: Long,
    val image: String?
)
