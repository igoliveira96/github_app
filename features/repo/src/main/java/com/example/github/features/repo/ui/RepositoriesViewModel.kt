package com.example.github.features.repo.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.github.core.uikit.BaseViewModel
import com.example.github.data.repo.model.Repo
import com.example.github.data.repo.model.User
import com.example.github.data.repo.repository.RepoRepository
import com.example.github.data.repo.usecase.GetUserUseCase
import com.example.github.features.repo.navigation.RepositoriesNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase,
    private val repository: RepoRepository,
    private val navigation: RepositoriesNavigation
) : BaseViewModel<RepoUIState, RepoUIEvent>() {

    override val _state: MutableStateFlow<RepoUIState> = MutableStateFlow(RepoUIState())
    override val state: StateFlow<RepoUIState>
        get() = _state

    init {
        savedStateHandle.get<String>("login")?.let { login ->
            getRepositories(login)
            viewModelScope.launch { getUser(login) }
        }
    }

    override fun sendEvent(event: RepoUIEvent) {
        when(event) {
            is RepoUIEvent.PopBackStack -> navigation.popBackStack()
        }
    }

    private fun getRepositories(login: String) {
        _state.update {
            it.copy(
                repos = repository.getRepositories(login).cachedIn(viewModelScope)
            )
        }
    }

    private suspend fun getUser(login: String) {
        getUserUseCase(
            params = GetUserUseCase.Params(login),
            onSuccess = { user ->
                _state.update { it.copy(user = user) }
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    private fun openURL() {

    }

}