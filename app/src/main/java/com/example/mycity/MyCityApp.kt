package com.example.mycity

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.data.Category
import com.example.mycity.ui.CategoryListScreen
import com.example.mycity.ui.MyCityUiState
import com.example.mycity.ui.MyCityViewModel
import com.example.mycity.ui.RecommendationDetailScreen
import com.example.mycity.ui.RecommendationListAndDetailScreen
import com.example.mycity.ui.RecommendationListScreen
import com.example.mycity.ui.icon
import com.example.mycity.ui.utils.MyCityContentType
import com.example.mycity.ui.utils.MyCityNavigationType

/**
 * Destinos de navegación de la app.
 */
enum class MyCityScreen {
    Categories,
    Recommendations,
    Detail
}

@Composable
fun MyCityAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

/**
 * Composable raíz. Igual que la app Reply, elige la navegación y el tipo de contenido
 * según el [WindowWidthSizeClass]:
 * - Compact: NavHost con categorías → recomendaciones → detalle.
 * - Medium: NavigationRail con las categorías + NavHost con recomendaciones → detalle.
 * - Expanded: PermanentNavigationDrawer con las categorías + lista y detalle juntos.
 */
@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    viewModel: MyCityViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    val navigationType: MyCityNavigationType
    val contentType: MyCityContentType
    when (windowSize) {
        WindowWidthSizeClass.Medium -> {
            navigationType = MyCityNavigationType.NAVIGATION_RAIL
            contentType = MyCityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = MyCityContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = MyCityNavigationType.SCREEN_FLOW
            contentType = MyCityContentType.LIST_ONLY
        }
    }

    val onCategorySelected: (Category) -> Unit = { category ->
        viewModel.selectCategory(category)
        if (navigationType == MyCityNavigationType.NAVIGATION_RAIL) {
            // Vuelve a la lista si el usuario estaba viendo un detalle
            navController.popBackStack(MyCityScreen.Recommendations.name, inclusive = false)
        }
    }

    when (navigationType) {
        MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            PermanentNavigationDrawer(
                drawerContent = {
                    PermanentDrawerSheet(Modifier.width(280.dp)) {
                        Text(
                            text = stringResource(R.string.app_name),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(24.dp)
                        )
                        uiState.categories.forEach { category ->
                            NavigationDrawerItem(
                                label = { Text(stringResource(category.title)) },
                                icon = { Icon(category.icon, contentDescription = null) },
                                selected = category == uiState.currentCategory,
                                onClick = { onCategorySelected(category) },
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                        }
                    }
                }
            ) {
                Scaffold(
                    topBar = {
                        MyCityAppBar(
                            title = stringResource(uiState.currentCategory.title),
                            canNavigateBack = false,
                            navigateUp = {}
                        )
                    }
                ) { innerPadding ->
                    if (contentType == MyCityContentType.LIST_AND_DETAIL) {
                        RecommendationListAndDetailScreen(
                            recommendations = uiState.recommendations,
                            selectedRecommendation = uiState.currentRecommendation,
                            onRecommendationClick = { viewModel.selectRecommendation(it) },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }

        MyCityNavigationType.NAVIGATION_RAIL -> {
            Row(Modifier.fillMaxSize()) {
                NavigationRail {
                    uiState.categories.forEach { category ->
                        NavigationRailItem(
                            selected = category == uiState.currentCategory,
                            onClick = { onCategorySelected(category) },
                            icon = { Icon(category.icon, contentDescription = null) },
                            label = { Text(stringResource(category.title)) }
                        )
                    }
                }
                MyCityNavHost(
                    uiState = uiState,
                    viewModel = viewModel,
                    navController = navController,
                    startDestination = MyCityScreen.Recommendations,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        MyCityNavigationType.SCREEN_FLOW -> {
            MyCityNavHost(
                uiState = uiState,
                viewModel = viewModel,
                navController = navController,
                startDestination = MyCityScreen.Categories
            )
        }
    }
}

/**
 * Scaffold + NavHost usado en pantallas compactas y medianas.
 */
@Composable
private fun MyCityNavHost(
    uiState: MyCityUiState,
    viewModel: MyCityViewModel,
    navController: NavHostController,
    startDestination: MyCityScreen,
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: startDestination.name
    )
    val title = when (currentScreen) {
        MyCityScreen.Categories -> stringResource(R.string.app_name)
        MyCityScreen.Recommendations -> stringResource(uiState.currentCategory.title)
        MyCityScreen.Detail -> uiState.currentRecommendation?.let { stringResource(it.name) }
            ?: stringResource(R.string.app_name)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            MyCityAppBar(
                title = title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityScreen.Categories.name) {
                CategoryListScreen(
                    categories = uiState.categories,
                    onCategoryClick = {
                        viewModel.selectCategory(it)
                        navController.navigate(MyCityScreen.Recommendations.name)
                    },
                    contentPadding = PaddingValues(vertical = 16.dp),
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = MyCityScreen.Recommendations.name) {
                RecommendationListScreen(
                    recommendations = uiState.recommendations,
                    onRecommendationClick = {
                        viewModel.selectRecommendation(it)
                        navController.navigate(MyCityScreen.Detail.name)
                    },
                    contentPadding = PaddingValues(vertical = 16.dp),
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = MyCityScreen.Detail.name) {
                RecommendationDetailScreen(recommendation = uiState.currentRecommendation)
            }
        }
    }
}
