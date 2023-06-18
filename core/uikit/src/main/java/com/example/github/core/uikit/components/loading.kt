package com.example.github.core.uikit.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.example.github.core.uikit.theme.GithubTheme.colors

@Composable
fun LoadingView() {
    CircularProgressIndicator(
        color = colors.white50Percent
    )
}