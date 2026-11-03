package com.example.mycity.ui

import com.example.mycity.data.Category
import com.example.mycity.data.Recommendation

/**
 * Estado inmutable que la UI dibuja. Solo el [MyCityViewModel] lo modifica.
 */
data class MyCityUiState(
    val categories: List<Category> = emptyList(),
    val currentCategory: Category = Category.BEACHES,
    val recommendations: List<Recommendation> = emptyList(),
    val currentRecommendation: Recommendation? = null
)
