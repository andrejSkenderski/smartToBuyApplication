package com.finki.smartToBuyApp.repository

import com.finki.smartToBuyApp.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long>