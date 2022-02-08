package com.example.quickchat.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPallete = lightColors(
    background = ghost_white,
    primary = medium_purple,
    primaryVariant = maximum_blue_purple,
    secondary = unbleached_silk,
    secondaryVariant = antique_white,

)
private val DarkColorPallete = lightColors(
    background = ghost_white,
    primary = medium_purple,
    primaryVariant = maximum_blue_purple,
    secondary = unbleached_silk,
    secondaryVariant = antique_white,

)

@Composable
fun QuickChatTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {


    MaterialTheme(
        colors = LightColorPallete,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}