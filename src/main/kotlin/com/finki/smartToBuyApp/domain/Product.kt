package com.finki.smartToBuyApp.domain

import javax.persistence.*

@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    val id: Long = 0,

    @Column(name = "name")
    val name: String,

    @Column(name = "description")
    val description: String,

    @Column(name = "price")
    val price: Int,

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category


)