package com.example.mycity.data

import androidx.annotation.StringRes

/**
 * Un lugar recomendado dentro de una [Category].
 */
data class Recommendation(
    val id: Int,
    val category: Category,
    @StringRes val name: Int,
    @StringRes val summary: Int,
    @StringRes val details: Int,
    @StringRes val location: Int
)
