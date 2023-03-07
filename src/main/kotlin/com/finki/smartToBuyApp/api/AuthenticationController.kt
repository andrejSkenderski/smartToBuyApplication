package com.finki.smartToBuyApp.api

import com.finki.smartToBuyApp.api.authentication.domain.AuthenticationRequest
import com.finki.smartToBuyApp.api.authentication.domain.AuthenticationResponse
import com.finki.smartToBuyApp.service.MyUserService
import com.finki.smartToBuyApp.security.util.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class AuthenticationController(
    private val myUserService: MyUserService,
    private val jwtTokenUtil: JwtUtil,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/authenticate")
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<Any> {
        try {
            this.authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(authenticationRequest.email, authenticationRequest.password)
            )
        } catch (exception: Exception) {
            return ResponseEntity.badRequest().body("Invalid username or password")
        }

        val userDetails = this.myUserService.loadUserByUsername(authenticationRequest.email)
        val jwt = this.jwtTokenUtil.generateToken(userDetails)
        val expiresIn = this.jwtTokenUtil.extractExpiration(jwt)
        return ResponseEntity.ok(AuthenticationResponse(jwt, expiresIn))
    }
}