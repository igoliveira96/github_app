package com.example.github.features.repo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.github.core.uikit.components.ShowError
import com.example.github.core.uikit.components.ShowLoading
import com.example.github.core.uikit.theme.GithubTheme.colors
import com.example.github.core.uikit.utils.openURL
import com.example.github.features.repo.R
import com.example.github.features.repo.components.RepoCard
import com.example.github.features.repo.components.RepoHeader

@Composable
fun RepositoriesScreen(viewModel: RepositoriesViewModel) {
    val state = viewModel.state.collectAsState()
    val sendEvent = viewModel::sendEvent
    RepositoriesScreenContent(state.value, sendEvent)
}

@Composable
private fun RepositoriesScreenContent(state: RepoUIState, sendEvent: (RepoUIEvent) -> Unit) {
    val context = LocalContext.current
    val lazyPagingItems = state.repos?.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = colors.backgroundColor,
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(top = 12.dp),
                title = { },
                navigationIcon = {
                    IconButton(onClick = { sendEvent(RepoUIEvent.PopBackStack) }) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                backgroundColor = colors.backgroundColor,
                contentColor = Color.White,
                elevation = 0.dp
            )
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            lazyPagingItems?.let {
                LazyColumn(
                    modifier = Modifier.padding(bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    item {
                        state.user?.let { user ->
                            RepoHeader(user)
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(lazyPagingItems) { user ->
                        user?.let {
                            RepoCard(it) { repo -> openURL(context, repo.repoURL) }
                        }
                    }

                    lazyPagingItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item {
                                    ShowLoading(modifier = Modifier.fillParentMaxSize())
                                }
                            }
                            loadState.append is LoadState.Loading -> {
                                item {
                                    ShowLoading(modifier = Modifier.fillParentMaxWidth())
                                }
                            }
                            loadState.refresh is LoadState.Error -> {
                                val e = lazyPagingItems.loadState.refresh as LoadState.Error
                                item {
                                    ShowError(
                                        message = e.error.localizedMessage ?: stringResource(R.string.error_message),
                                        modifier = Modifier.fillParentMaxSize(),
                                        onClickRetry = { retry() }
                                    )
                                }
                            }
                            loadState.append is LoadState.Error -> {
                                val e = lazyPagingItems.loadState.append as LoadState.Error
                                item {
                                    ShowError(
                                        message = e.error.localizedMessage ?: stringResource(R.string.error_message),
                                        onClickRetry = { retry() }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}