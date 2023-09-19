package com.finki.smartToBuyApp.repository

import com.finki.smartToBuyApp.domain.CartItem
import com.finki.smartToBuyApp.domain.CartItemStatus
import com.finki.smartToBuyApp.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface CartItemRepository : JpaRepository<CartItem, Long> {
    fun findByUserAndStatus(user: User, status: CartItemStatus): CartItem?
}