package com.finki.smartToBuyApp.api.request

data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
    val mobile: String,
    val email: String,
    val password: String
)