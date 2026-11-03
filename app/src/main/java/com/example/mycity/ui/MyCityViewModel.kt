package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.Category
import com.example.mycity.data.LocalMyCityRepository
import com.example.mycity.data.MyCityRepository
import com.example.mycity.data.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel de la app. Sigue el flujo unidireccional de datos (UDF):
 * la UI envía eventos con [selectCategory] y [selectRecommendation], y recibe el estado
 * actualizado a través de [uiState].
 */
class MyCityViewModel(
    private val repository: MyCityRepository = LocalMyCityRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState.asStateFlow()

    init {
        val categories = repository.getCategories()
        val firstCategory = categories.first()
        val recommendations = repository.getRecommendations(firstCategory)
        _uiState.value = MyCityUiState(
            categories = categories,
            currentCategory = firstCategory,
            recommendations = recommendations,
            currentRecommendation = recommendations.firstOrNull()
        )
    }

    /** Cambia la categoría y muestra su primera recomendación en el panel de detalle. */
    fun selectCategory(category: Category) {
        val recommendations = repository.getRecommendations(category)
        _uiState.update {
            it.copy(
                currentCategory = category,
                recommendations = recommendations,
                currentRecommendation = recommendations.firstOrNull()
            )
        }
    }

    /** Marca la recomendación que se ve en la pantalla de detalle. */
    fun selectRecommendation(recommendation: Recommendation) {
        _uiState.update { it.copy(currentRecommendation = recommendation) }
    }
}
