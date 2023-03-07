package com.finki.smartToBuyApp.domain

import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "purchases")
data class Purchase(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product,

    @Column(name = "purchase_date")
    val purchaseDate: ZonedDateTime,

    @Column(name = "purchase_amount")
    val purchaseAmount: Double
)