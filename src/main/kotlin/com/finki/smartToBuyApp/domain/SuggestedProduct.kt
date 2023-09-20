package com.finki.smartToBuyApp.domain

import javax.persistence.*

@Entity
@Table(name = "suggested_products")
data class SuggestedProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product
)