package com.example.github.core.uikit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.github.core.uikit.R
import com.example.github.core.uikit.theme.GithubTheme

@Composable
fun ShowError(
    modifier: Modifier = Modifier,
    message: String,
    onClickRetry: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            color = GithubTheme.colors.imperialRed,
            style = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.W500
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onClickRetry,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = GithubTheme.colors.lotion20Percent,
            )
        ) {
            Text(
                text = stringResource(R.string.try_again),
                color = Color.White,
                style = TextStyle.Default.copy(
                    fontSize = 16.sp
                )
            )
        }
    }
}