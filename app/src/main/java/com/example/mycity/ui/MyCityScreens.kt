package com.example.mycity.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BeachAccess
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.data.Category
import com.example.mycity.data.LocalRecommendationsDataProvider
import com.example.mycity.data.Recommendation
import com.example.mycity.ui.theme.MyCityTheme

/** Ícono que representa cada categoría en la UI. */
val Category.icon: ImageVector
    get() = when (this) {
        Category.BEACHES -> Icons.Filled.BeachAccess
        Category.FOOD -> Icons.Filled.Restaurant
        Category.CULTURE -> Icons.Filled.Museum
        Category.NATURE -> Icons.Filled.Forest
        Category.SHOPPING -> Icons.Filled.LocalMall
    }

/**
 * Pantalla 1: lista de categorías.
 */
@Composable
fun CategoryListScreen(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
    selectedCategory: Category? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = stringResource(R.string.home_title),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = stringResource(R.string.home_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        items(categories, key = { it.name }) { category ->
            CategoryCard(
                category = category,
                selected = category == selectedCategory,
                onClick = { onCategoryClick(category) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun CategoryCard(
    category: Category,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = category.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = stringResource(category.title),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(category.description),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = null)
        }
    }
}

/**
 * Pantalla 2: recomendaciones de la categoría seleccionada.
 */
@Composable
fun RecommendationListScreen(
    recommendations: List<Recommendation>,
    onRecommendationClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier,
    selectedRecommendation: Recommendation? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(recommendations, key = { it.id }) { recommendation ->
            RecommendationCard(
                recommendation = recommendation,
                selected = recommendation.id == selectedRecommendation?.id,
                onClick = { onRecommendationClick(recommendation) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun RecommendationCard(
    recommendation: Recommendation,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.secondaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = recommendation.category.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = stringResource(recommendation.name),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(recommendation.summary),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

/**
 * Pantalla 3: detalle de una recomendación.
 */
@Composable
fun RecommendationDetailScreen(
    recommendation: Recommendation?,
    modifier: Modifier = Modifier
) {
    if (recommendation == null) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(R.string.select_recommendation),
                modifier = Modifier.padding(24.dp)
            )
        }
        return
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = recommendation.category.icon,
                contentDescription = stringResource(recommendation.category.title),
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(96.dp)
            )
        }
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(recommendation.name),
                style = MaterialTheme.typography.headlineSmall
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Place,
                    contentDescription = stringResource(R.string.location_label),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = stringResource(recommendation.location),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Text(
                text = stringResource(R.string.about_label),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(recommendation.details),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

/**
 * Pantalla expandida (tablet / horizontal): lista y detalle uno al lado del otro.
 */
@Composable
fun RecommendationListAndDetailScreen(
    recommendations: List<Recommendation>,
    selectedRecommendation: Recommendation?,
    onRecommendationClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxSize()) {
        RecommendationListScreen(
            recommendations = recommendations,
            selectedRecommendation = selectedRecommendation,
            onRecommendationClick = onRecommendationClick,
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier.weight(1f)
        )
        RecommendationDetailScreen(
            recommendation = selectedRecommendation,
            modifier = Modifier.weight(1.4f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryListPreview() {
    MyCityTheme {
        CategoryListScreen(categories = Category.entries, onCategoryClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationDetailPreview() {
    MyCityTheme {
        RecommendationDetailScreen(
            recommendation = LocalRecommendationsDataProvider.recommendations.first()
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ListAndDetailPreview() {
    MyCityTheme {
        val recommendations = LocalRecommendationsDataProvider.recommendations
            .filter { it.category == Category.BEACHES }
        RecommendationListAndDetailScreen(
            recommendations = recommendations,
            selectedRecommendation = recommendations.first(),
            onRecommendationClick = {}
        )
    }
}
