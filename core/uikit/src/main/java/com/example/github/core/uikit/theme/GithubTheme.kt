package com.example.github.core.uikit.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val LocalAppColors = compositionLocalOf { AppColors() }

@Composable
fun GithubTheme(
    content: @Composable () -> Unit
) {
    val backgroundColor = GithubTheme.colors.backgroundColor
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = backgroundColor)
        systemUiController.setNavigationBarColor(color = Color.Black)
    }
    CompositionLocalProvider(
        LocalAppColors provides AppColors(),
    ) {
        MaterialTheme(content = content)
    }
}

object GithubTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current
}