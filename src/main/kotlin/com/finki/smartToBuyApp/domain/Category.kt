package com.finki.smartToBuyApp.domain

import javax.persistence.*

@Entity
@Table(name = "categories")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    val id: Long = 0,

    @Column(name = "name")
    val name: String
)