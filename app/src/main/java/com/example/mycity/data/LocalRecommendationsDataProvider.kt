package com.example.mycity.data

import com.example.mycity.R

/**
 * Fuente de datos local con las recomendaciones de Manta.
 */
object LocalRecommendationsDataProvider {

    val recommendations: List<Recommendation> = listOf(
        // Playas
        Recommendation(
            id = 1,
            category = Category.BEACHES,
            name = R.string.rec_murcielago,
            summary = R.string.rec_murcielago_summary,
            details = R.string.rec_murcielago_details,
            location = R.string.rec_murcielago_location
        ),
        Recommendation(
            id = 2,
            category = Category.BEACHES,
            name = R.string.rec_santa_marianita,
            summary = R.string.rec_santa_marianita_summary,
            details = R.string.rec_santa_marianita_details,
            location = R.string.rec_santa_marianita_location
        ),
        Recommendation(
            id = 3,
            category = Category.BEACHES,
            name = R.string.rec_san_lorenzo,
            summary = R.string.rec_san_lorenzo_summary,
            details = R.string.rec_san_lorenzo_details,
            location = R.string.rec_san_lorenzo_location
        ),
        Recommendation(
            id = 4,
            category = Category.BEACHES,
            name = R.string.rec_tarqui,
            summary = R.string.rec_tarqui_summary,
            details = R.string.rec_tarqui_details,
            location = R.string.rec_tarqui_location
        ),
        // Gastronomía
        Recommendation(
            id = 5,
            category = Category.FOOD,
            name = R.string.rec_playita_mia,
            summary = R.string.rec_playita_mia_summary,
            details = R.string.rec_playita_mia_details,
            location = R.string.rec_playita_mia_location
        ),
        Recommendation(
            id = 6,
            category = Category.FOOD,
            name = R.string.rec_malecon,
            summary = R.string.rec_malecon_summary,
            details = R.string.rec_malecon_details,
            location = R.string.rec_malecon_location
        ),
        Recommendation(
            id = 7,
            category = Category.FOOD,
            name = R.string.rec_barbasquillo,
            summary = R.string.rec_barbasquillo_summary,
            details = R.string.rec_barbasquillo_details,
            location = R.string.rec_barbasquillo_location
        ),
        // Cultura e historia
        Recommendation(
            id = 8,
            category = Category.CULTURE,
            name = R.string.rec_museo,
            summary = R.string.rec_museo_summary,
            details = R.string.rec_museo_details,
            location = R.string.rec_museo_location
        ),
        Recommendation(
            id = 9,
            category = Category.CULTURE,
            name = R.string.rec_astilleros,
            summary = R.string.rec_astilleros_summary,
            details = R.string.rec_astilleros_details,
            location = R.string.rec_astilleros_location
        ),
        Recommendation(
            id = 10,
            category = Category.CULTURE,
            name = R.string.rec_jaboncillo,
            summary = R.string.rec_jaboncillo_summary,
            details = R.string.rec_jaboncillo_details,
            location = R.string.rec_jaboncillo_location
        ),
        // Naturaleza
        Recommendation(
            id = 11,
            category = Category.NATURE,
            name = R.string.rec_pacoche,
            summary = R.string.rec_pacoche_summary,
            details = R.string.rec_pacoche_details,
            location = R.string.rec_pacoche_location
        ),
        Recommendation(
            id = 12,
            category = Category.NATURE,
            name = R.string.rec_ballenas,
            summary = R.string.rec_ballenas_summary,
            details = R.string.rec_ballenas_details,
            location = R.string.rec_ballenas_location
        ),
        Recommendation(
            id = 13,
            category = Category.NATURE,
            name = R.string.rec_atardecer,
            summary = R.string.rec_atardecer_summary,
            details = R.string.rec_atardecer_details,
            location = R.string.rec_atardecer_location
        ),
        // Compras
        Recommendation(
            id = 14,
            category = Category.SHOPPING,
            name = R.string.rec_mall_pacifico,
            summary = R.string.rec_mall_pacifico_summary,
            details = R.string.rec_mall_pacifico_details,
            location = R.string.rec_mall_pacifico_location
        ),
        Recommendation(
            id = 15,
            category = Category.SHOPPING,
            name = R.string.rec_paseo_shopping,
            summary = R.string.rec_paseo_shopping_summary,
            details = R.string.rec_paseo_shopping_details,
            location = R.string.rec_paseo_shopping_location
        ),
        Recommendation(
            id = 16,
            category = Category.SHOPPING,
            name = R.string.rec_montecristi,
            summary = R.string.rec_montecristi_summary,
            details = R.string.rec_montecristi_details,
            location = R.string.rec_montecristi_location
        )
    )
}
