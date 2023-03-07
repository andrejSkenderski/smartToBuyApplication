package com.finki.smartToBuyApp.repository

import com.finki.smartToBuyApp.domain.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductCategoryRepository : JpaRepository<ProductCategory, Long>