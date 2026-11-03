package com.example.mycity.data

import androidx.annotation.StringRes
import com.example.mycity.R

/**
 * Categorías de recomendaciones que ofrece la app.
 */
enum class Category(
    @StringRes val title: Int,
    @StringRes val description: Int
) {
    BEACHES(R.string.category_beaches, R.string.category_beaches_desc),
    FOOD(R.string.category_food, R.string.category_food_desc),
    CULTURE(R.string.category_culture, R.string.category_culture_desc),
    NATURE(R.string.category_nature, R.string.category_nature_desc),
    SHOPPING(R.string.category_shopping, R.string.category_shopping_desc)
}
