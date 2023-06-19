package com.example.github.features.home.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.github.core.uikit.components.*
import com.example.github.core.uikit.theme.GithubTheme.colors
import com.example.github.features.home.R
import com.example.github.features.home.components.UserCard

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state = viewModel.state.collectAsState()
    HomeScreenContent(state.value, viewModel::sendEvent)
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun HomeScreenContent(state: HomeUIState, sendEvent: (HomeUIEvent) -> Unit) {
    val lazyPagingItems = state.users?.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopAppBar(
                pageTitle = stringResource(R.string.page_title),
                searchAppBarState = state.searchAppBarState,
                searchTextState = state.searchAppBar,
                changeState = { state ->
                    sendEvent(
                        HomeUIEvent.UpdatedAppBarState(state)
                    )
                },
                changeText = { search ->
                    sendEvent(
                        HomeUIEvent.UpdatedSearchAppBar(search)
                    )
                }
            ) { sendEvent(HomeUIEvent.SearchUser) }
        },
        backgroundColor = colors.backgroundColor
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(top = 24.dp),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            lazyPagingItems?.let {
                items(lazyPagingItems) { user ->
                    user?.let {
                        UserCard(it) { userClicked ->
                            sendEvent(HomeUIEvent.ShowRepositories(userClicked))
                        }
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
