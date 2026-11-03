package com.example.mycity.data

/**
 * Repositorio de la capa de datos. La capa de UI solo accede a los datos a través de esta interfaz.
 */
interface MyCityRepository {
    fun getCategories(): List<Category>
    fun getRecommendations(category: Category): List<Recommendation>
    fun getRecommendation(id: Int): Recommendation?
}

/**
 * Implementación que lee los datos locales de [LocalRecommendationsDataProvider].
 */
class LocalMyCityRepository(
    private val recommendations: List<Recommendation> = LocalRecommendationsDataProvider.recommendations
) : MyCityRepository {

    override fun getCategories(): List<Category> = Category.entries

    override fun getRecommendations(category: Category): List<Recommendation> =
        recommendations.filter { it.category == category }

    override fun getRecommendation(id: Int): Recommendation? =
        recommendations.firstOrNull { it.id == id }
}
