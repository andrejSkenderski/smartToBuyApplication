package com.finki.smartToBuyApp.api.authentication.domain

import java.util.*

data class AuthenticationResponse(val jwt: String, val expiresIn: Date)