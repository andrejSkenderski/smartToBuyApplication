package com.finki.smartToBuyApp.domain

import javax.persistence.*

@Entity
@Table(name = "product_categories")
data class ProductCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "name")
    val name: String
)