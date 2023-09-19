package com.finki.smartToBuyApp.domain

import javax.persistence.*

@Entity
@Table(name = "cart_items")
data class CartItem(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id",columnDefinition = "SERIAL")
        val id: Long = 0,

        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: User,

        @ManyToMany
        @JoinTable(
                name = "cart_item_products",
                joinColumns = [JoinColumn(name = "cart_item_id")],
                inverseJoinColumns = [JoinColumn(name = "product_id")]
        )
        var products: List<Product>,

        @Enumerated(EnumType.ORDINAL)
        var status: CartItemStatus = CartItemStatus.ACTIVE
)

enum class CartItemStatus {
    ACTIVE, // Cart is active
    INACTIVE, // Cart is no longer active
    PENDING, // Cart is in a pending state (optional)
    PURCHASED
}