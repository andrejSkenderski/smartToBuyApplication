package com.finki.smartToBuyApp.api

import com.finki.smartToBuyApp.domain.CartItem
import com.finki.smartToBuyApp.service.CartService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cart")
class CartItemController(private val cartService: CartService) {

    @PostMapping("/add/{productId}")
    fun addToCart(@PathVariable productId: Long) = cartService.addToCart(productId)

    @DeleteMapping("/remove/{productId}")
    fun removeFromCart(@PathVariable productId: Long) = cartService.removeFromCart(productId)

    @PostMapping("/purchase")
    fun purchaseCartItems() = cartService.purchase()

    @GetMapping("/items")
    fun getCartItems(): CartItem = cartService.getCartItems()

}
