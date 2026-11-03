package com.example.mycity.ui.utils

/**
 * Tipo de navegación según el ancho de la ventana (igual que en la app Reply).
 */
enum class MyCityNavigationType {
    /** Pantalla compacta: categorías → lista → detalle con NavHost. */
    SCREEN_FLOW,
    /** Pantalla mediana: categorías en un NavigationRail. */
    NAVIGATION_RAIL,
    /** Pantalla expandida: categorías en un PermanentNavigationDrawer. */
    PERMANENT_NAVIGATION_DRAWER
}

/**
 * Tipo de contenido: solo la lista o la lista junto al detalle.
 */
enum class MyCityContentType {
    LIST_ONLY,
    LIST_AND_DETAIL
}
