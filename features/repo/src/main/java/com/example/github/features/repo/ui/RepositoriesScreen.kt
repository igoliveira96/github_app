package com.example.github.features.repo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.github.core.uikit.R
import com.example.github.core.uikit.theme.GithubTheme.colors
import com.example.github.data.repo.model.Repo
import com.example.github.data.repo.model.User
import com.example.github.features.repo.components.RepoCard
import com.example.github.features.repo.components.RepoHeader

@Composable
fun RepositoriesScreen(viewModel: RepositoriesViewModel) {
    val state = viewModel.state.collectAsState()
    RepositoriesScreenContent(state.value)
}

@Composable
private fun RepositoriesScreenContent(state: RepositoriesUIState) {
    Scaffold(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = colors.backgroundColor,
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            state.user?.let { user ->
                RepoHeader(user)
            }
            
            Spacer(modifier = Modifier.height(8.dp))

            RepoCard(Repo.EXAMPLE) { }
        }
    }
}