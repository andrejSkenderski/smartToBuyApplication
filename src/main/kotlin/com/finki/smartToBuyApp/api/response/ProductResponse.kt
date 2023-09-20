package com.finki.smartToBuyApp.api.response

import com.finki.smartToBuyApp.domain.Product


data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val price: Double,
    val category: String,
    val image: String?
)

fun Product.toResponse(): ProductResponse =
    ProductResponse(
        id = id,
        name = name,
        description = description,
        price = price,
        category = category.name,
        image = image
    )


