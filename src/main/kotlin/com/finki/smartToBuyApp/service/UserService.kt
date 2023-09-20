package com.finki.smartToBuyApp.service

import com.finki.smartToBuyApp.api.request.CreateUserRequest
import com.finki.smartToBuyApp.api.response.GetUserResponse
import com.finki.smartToBuyApp.api.response.GetUserResponseFailed
import com.finki.smartToBuyApp.api.response.GetUserResponseSuccess
import com.finki.smartToBuyApp.domain.User
import com.finki.smartToBuyApp.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class UserService(
    val repository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {
    val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

    fun getUserByEmail(email: String): User? {
        return repository.findByEmail(email)
    }

    fun getLoggedInUser(): GetUserResponse {
        val username = SecurityContextHolder.getContext().authentication.name
        return when (val user = getUserByEmail(username)) {
            is User -> GetUserResponseSuccess(user)
            else -> GetUserResponseFailed("No user with that email")
        }
    }

    //TODO: Oliver refactor this code
    fun createUser(request: CreateUserRequest): User {
        if (getUserByEmail(request.email) != null) {
            throw RuntimeException("User already exists")
        }

        val user = User(
            0,
            request.firstName,
            request.lastName,
            request.mobile,
            request.email,
            passwordEncoder.encode(request.password)
        )
        logger.info("Creating user: [{}]", user)
        return repository.save(user)
    }

    fun findById(id: Long): User {
        return repository.findByIdOrNull(id) ?: throw EntityNotFoundException("User with id $id is not found.")
    }

    fun findAll(): List<User> = repository.findAll()
}