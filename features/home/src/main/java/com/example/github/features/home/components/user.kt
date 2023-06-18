package com.example.github.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.github.core.uikit.R
import com.example.github.core.uikit.theme.GithubTheme.colors
import com.example.github.data.repo.model.User

@Composable
fun UserCard(user: User, onClick: (User) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick(user) },
        shape = RoundedCornerShape(24.dp),
        backgroundColor = colors.yankeesBlue,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatarURL)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_user_fallback),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(60.dp).clip(CircleShape)
            )
            Text(
                text = user.login,
                color = Color.White,
                style = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                )
            )
        }
    }
}

@Preview
@Composable
fun UserCardPreview() {
    UserCard(User.EXAMPLE) { }
}