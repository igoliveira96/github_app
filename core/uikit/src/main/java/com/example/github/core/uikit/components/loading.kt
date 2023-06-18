package com.example.github.core.uikit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.github.core.uikit.theme.GithubTheme.colors

@Composable
fun LoadingView() {
    CircularProgressIndicator(
        color = colors.white50Percent
    )
}

@Composable
fun ShowLoading(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LoadingView()
    }
}
