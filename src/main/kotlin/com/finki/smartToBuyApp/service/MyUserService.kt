package com.finki.smartToBuyApp.service

import com.finki.smartToBuyApp.repository.UserRepository
import com.finki.smartToBuyApp.util.MyUserDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUserService(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username) ?: throw RuntimeException("User with username $username is not found")
        return MyUserDetails(user)
    }
}