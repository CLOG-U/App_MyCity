package com.example.mycity.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = OceanPrimary,
    onPrimary = OceanOnPrimary,
    primaryContainer = OceanPrimaryContainer,
    onPrimaryContainer = OceanOnPrimaryContainer,
    secondary = SandSecondary,
    onSecondary = SandOnSecondary,
    secondaryContainer = SandSecondaryContainer,
    onSecondaryContainer = SandOnSecondaryContainer,
    tertiary = CoralTertiary,
    tertiaryContainer = CoralTertiaryContainer,
    background = LightBackground,
    surface = LightBackground,
    surfaceVariant = LightSurfaceVariant
)

private val DarkColors = darkColorScheme(
    primary = OceanPrimaryDark,
    onPrimary = OceanOnPrimaryDark,
    primaryContainer = OceanPrimaryContainerDark,
    onPrimaryContainer = OceanOnPrimaryContainerDark,
    secondary = SandSecondaryDark,
    onSecondary = SandOnSecondaryDark,
    secondaryContainer = SandSecondaryContainerDark,
    onSecondaryContainer = SandOnSecondaryContainerDark,
    tertiary = CoralTertiaryDark,
    tertiaryContainer = CoralTertiaryContainerDark,
    background = DarkBackground,
    surface = DarkBackground,
    surfaceVariant = DarkSurfaceVariant
)

@Composable
fun MyCityTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}
