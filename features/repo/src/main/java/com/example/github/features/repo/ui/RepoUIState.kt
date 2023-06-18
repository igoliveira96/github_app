package com.example.github.features.repo.ui

import androidx.paging.PagingData
import com.example.github.core.uikit.UIState
import com.example.github.data.repo.model.Repo
import com.example.github.data.repo.model.User
import kotlinx.coroutines.flow.Flow

data class RepoUIState(
    val user: User? = null,
    val repos: Flow<PagingData<Repo>>? = null
) : UIState
