package com.example.mycity

import com.example.mycity.data.Category
import com.example.mycity.data.LocalMyCityRepository
import com.example.mycity.ui.MyCityViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MyCityViewModelTest {

    private val repository = LocalMyCityRepository()

    @Test
    fun init_loadsCategoriesAndFirstCategory() {
        val viewModel = MyCityViewModel(repository)
        val state = viewModel.uiState.value

        assertEquals(Category.entries, state.categories)
        assertEquals(Category.BEACHES, state.currentCategory)
        assertTrue(state.recommendations.all { it.category == Category.BEACHES })
        assertEquals(state.recommendations.first(), state.currentRecommendation)
    }

    @Test
    fun selectCategory_updatesRecommendations() {
        val viewModel = MyCityViewModel(repository)

        viewModel.selectCategory(Category.FOOD)
        val state = viewModel.uiState.value

        assertEquals(Category.FOOD, state.currentCategory)
        assertEquals(repository.getRecommendations(Category.FOOD), state.recommendations)
        assertEquals(state.recommendations.first(), state.currentRecommendation)
    }

    @Test
    fun selectRecommendation_updatesCurrentRecommendation() {
        val viewModel = MyCityViewModel(repository)
        val target = repository.getRecommendations(Category.NATURE).last()

        viewModel.selectRecommendation(target)

        assertEquals(target, viewModel.uiState.value.currentRecommendation)
    }

    @Test
    fun everyCategory_hasAtLeastThreeRecommendations() {
        Category.entries.forEach { category ->
            assertTrue(repository.getRecommendations(category).size >= 3)
        }
    }
}
