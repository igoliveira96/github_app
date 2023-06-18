package com.example.github.features.repo.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
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
import com.example.github.core.uikit.components.IconWithText
import com.example.github.core.uikit.R as RUiKit
import com.example.github.core.uikit.theme.GithubTheme.colors
import com.example.github.core.uikit.utils.toDate
import com.example.github.core.uikit.utils.toLongDate
import com.example.github.core.uikit.utils.toShortDate
import com.example.github.data.repo.model.Repo
import com.example.github.data.repo.model.User

@Composable
fun RepoHeader(user: User) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.avatarURL)
                .crossfade(true)
                .build(),
            placeholder = painterResource(RUiKit.drawable.ic_user_fallback),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${user.name}${user.location?.let { " | $it" }}",
            color = Color.White,
            style = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            )
        )
        Text(
            text = user.login,
            color = colors.lotion20Percent,
            style = TextStyle.Default.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400
            )
        )
        Divider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp, color = colors.white50Percent
        )
    }
}

@Composable
fun RepoCard(repo: Repo, openRepo: (Repo) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = colors.yankeesBlue,
    ) {
        Column {
            Row(horizontalArrangement = Arrangement.End) {
                repo.createdAt.toDate()?.let { date ->
                    CreatedAt(date.toLongDate())
                }
            }
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = repo.name,
                    color = Color.White,
                    style = TextStyle.Default.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400
                    )
                )
                repo.description?.let { description ->
                    Text(
                        text = description,
                        color = Color.White,
                        style = TextStyle.Default.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400
                        )
                    )
                }
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp, color = colors.white50Percent
                )
                RepoCardFooter(repo) { openRepo(repo) }
            }
        }
    }
}

@Composable
private fun CreatedAt(createdAt: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Surface(
            shape = RoundedCornerShape(bottomStart = 16.dp, topEnd = 24.dp),
            color = colors.lotion20Percent
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                color = Color.White,
                style = TextStyle.Default.copy(
                    fontSize = 8.sp,
                    fontWeight = FontWeight.W700
                ),
                text = createdAt
            )
        }
    }
}

@Composable
private fun RepoCardFooter(repo: Repo, openRepo: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconWithText(
                icon = RUiKit.drawable.ic_star,
                text = repo.stargazersCount.toString()
            )
            repo.language?.let { language ->
                IconWithText(
                    icon = RUiKit.drawable.ic_code,
                    text = language
                )
            }
        }

        IconButton(onClick = { openRepo() }) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = RUiKit.drawable.ic_link),
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun RepoCardPreview() {
    RepoCard(Repo.EXAMPLE) { }
}