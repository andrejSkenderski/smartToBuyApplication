package com.finki.smartToBuyApp.domain

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "last_name")
    val lastName: String,

    @Column(name = "mobile")
    val mobile: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String
)