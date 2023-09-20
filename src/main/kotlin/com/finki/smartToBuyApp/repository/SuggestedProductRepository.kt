package com.finki.smartToBuyApp.repository

import com.finki.smartToBuyApp.domain.SuggestedProduct
import com.finki.smartToBuyApp.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SuggestedProductRepository : JpaRepository<SuggestedProduct, Long> {
    fun findAllByUser(user: User): List<SuggestedProduct>
}