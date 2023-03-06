package com.finki.smartToBuyApp.api

import com.finki.smartToBuyApp.api.request.CreateUserRequest
import com.finki.smartToBuyApp.api.response.GetUserResponse
import com.finki.smartToBuyApp.api.response.GetUserResponseFailed
import com.finki.smartToBuyApp.api.response.GetUserResponseSuccess
import com.finki.smartToBuyApp.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @PostMapping("/create")
    fun createUser(@RequestBody userRequest: CreateUserRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok().body(userService.createUser(userRequest))
        } catch (exception: RuntimeException) {
            ResponseEntity.badRequest().body(exception.message)
        }
    }

    @GetMapping("/get")
    fun getUser(): ResponseEntity<GetUserResponse> = when (val user = userService.getLoggedInUser()) {
        is GetUserResponseSuccess -> ResponseEntity.ok(user)
        is GetUserResponseFailed -> ResponseEntity.badRequest().body(user)
    }

}