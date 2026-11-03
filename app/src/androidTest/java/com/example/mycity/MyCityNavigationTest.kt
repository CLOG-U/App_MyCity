package com.example.mycity

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Pruebas de navegación y de diseño adaptable de My City.
 */
class MyCityNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    private fun string(id: Int) = composeTestRule.activity.getString(id)

    private fun setContent(windowSize: WindowWidthSizeClass) {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            MyCityApp(windowSize = windowSize, navController = navController)
        }
    }

    private fun assertRoute(screen: MyCityScreen) {
        assertEquals(screen.name, navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun compact_startDestinationIsCategories() {
        setContent(WindowWidthSizeClass.Compact)
        assertRoute(MyCityScreen.Categories)
        composeTestRule.onNodeWithContentDescription(string(R.string.back_button))
            .assertDoesNotExist()
    }

    @Test
    fun compact_clickCategory_navigatesToRecommendations() {
        setContent(WindowWidthSizeClass.Compact)
        composeTestRule.onNodeWithText(string(R.string.category_food)).performClick()
        assertRoute(MyCityScreen.Recommendations)
        composeTestRule.onNodeWithText(string(R.string.rec_playita_mia)).assertIsDisplayed()
    }

    @Test
    fun compact_clickRecommendation_navigatesToDetail() {
        setContent(WindowWidthSizeClass.Compact)
        composeTestRule.onNodeWithText(string(R.string.category_food)).performClick()
        composeTestRule.onNodeWithText(string(R.string.rec_malecon)).performClick()
        assertRoute(MyCityScreen.Detail)
        composeTestRule.onNodeWithText(string(R.string.rec_malecon_details)).assertIsDisplayed()
    }

    @Test
    fun compact_backFromDetail_returnsToRecommendations() {
        setContent(WindowWidthSizeClass.Compact)
        composeTestRule.onNodeWithText(string(R.string.category_food)).performClick()
        composeTestRule.onNodeWithText(string(R.string.rec_malecon)).performClick()
        composeTestRule.onNodeWithContentDescription(string(R.string.back_button)).performClick()
        assertRoute(MyCityScreen.Recommendations)
    }

    @Test
    fun medium_startsOnRecommendationsWithRail() {
        setContent(WindowWidthSizeClass.Medium)
        assertRoute(MyCityScreen.Recommendations)
        composeTestRule.onNodeWithText(string(R.string.category_shopping)).performClick()
        composeTestRule.onNodeWithText(string(R.string.rec_mall_pacifico)).assertIsDisplayed()
    }

    @Test
    fun expanded_showsListAndDetailTogether() {
        setContent(WindowWidthSizeClass.Expanded)
        composeTestRule.onNodeWithText(string(R.string.category_culture)).performClick()
        composeTestRule.onNodeWithText(string(R.string.rec_astilleros)).performClick()
        // La lista y el detalle se componen al mismo tiempo, sin navegar a otra pantalla
        // (en el emulador de teléfono el panel de detalle puede quedar fuera del área visible).
        composeTestRule.onNodeWithText(string(R.string.rec_museo)).assertIsDisplayed()
        composeTestRule.onNodeWithText(string(R.string.rec_astilleros_details)).assertExists()
    }
}
