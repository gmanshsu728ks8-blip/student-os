package com.studentos.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

/** Dark-only for now. A light scheme is intentionally not defined yet. */
private val StudentOsDarkColorScheme = darkColorScheme(
    primary = Gold,
    onPrimary = OnGold,
    primaryContainer = GoldContainer,
    onPrimaryContainer = GoldLight,
    secondary = GoldMuted,
    onSecondary = OnGold,
    background = Ink,
    onBackground = Ivory,
    surface = Charcoal,
    onSurface = Ivory,
    surfaceVariant = CharcoalHigh,
    onSurfaceVariant = Stone,
    surfaceContainerLowest = Ink,
    surfaceContainerLow = Charcoal,
    surfaceContainer = CharcoalRaised,
    surfaceContainerHigh = CharcoalHigh,
    surfaceContainerHighest = CharcoalHigh,
    outline = HairlineStrong,
    outlineVariant = Hairline,
    error = ErrorRed,
    onError = OnErrorRed,
)

@Composable
fun StudentOsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = StudentOsDarkColorScheme,
        typography = StudentOsTypography,
        shapes = StudentOsShapes,
        content = content,
    )
}
