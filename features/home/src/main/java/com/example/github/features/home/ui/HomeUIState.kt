package com.example.github.features.home.ui

import androidx.paging.PagingData
import com.example.github.core.uikit.UIState
import com.example.github.core.uikit.utils.SearchAppBarState
import com.example.github.data.repo.model.User
import kotlinx.coroutines.flow.Flow

data class HomeUIState(
    val searchAppBar: String = "",
    val searchAppBarState: SearchAppBarState = SearchAppBarState.CLOSED,
    val users: Flow<PagingData<User>>? = null
) : UIState