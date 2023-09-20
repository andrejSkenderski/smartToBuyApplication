package com.finki.smartToBuyApp.service

import com.finki.smartToBuyApp.domain.Product
import com.finki.smartToBuyApp.domain.SuggestedProduct
import com.finki.smartToBuyApp.domain.User
import com.finki.smartToBuyApp.repository.SuggestedProductRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class SuggestedProductService(
    private val repository: SuggestedProductRepository,
    private val userService: UserService
) {

    object SuggestedProductsConstants {
        const val NUMBER_OF_ELEMENTS = 5
    }

    fun save(user: User, product: Product): SuggestedProduct =
        repository.save(SuggestedProduct(user = user, product = product))

    fun getSuggestedProductsForActiveUser(): List<Product> {
            val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val username = userDetails.username
        val user = checkNotNull(userService.getUserByEmail(username))

        val suggestedProductsList = repository.findAllByUser(user).sortedByDescending { it.id }
            .take(SuggestedProductsConstants.NUMBER_OF_ELEMENTS)

        return suggestedProductsList.map { it.product }
    }

}