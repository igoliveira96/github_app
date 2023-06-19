package com.example.github.features.home.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.github.core.uikit.BaseViewModel
import com.example.github.data.repo.repository.RepoRepository
import com.example.github.features.home.navigation.HomeNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigation: HomeNavigation,
    private val repository: RepoRepository
) : BaseViewModel<HomeUIState, HomeUIEvent>() {

    override val _state: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState())
    override val state: StateFlow<HomeUIState>
        get() = _state

    init {
        getUsers()
    }

    private fun getUsers() {
        _state.update {
            it.copy(users = repository.getUsers().cachedIn(viewModelScope))
        }
    }

    override fun sendEvent(event: HomeUIEvent) {
        when(event) {
            is HomeUIEvent.ShowRepositories -> navigation.navigateToRepositories(
                event.user.login
            )
            is HomeUIEvent.UpdatedSearchAppBar -> {
                _state.update { it.copy(searchAppBar = event.search) }
            }
            is HomeUIEvent.UpdatedAppBarState -> {
                _state.update { it.copy(searchAppBarState = event.state) }
            }
            is HomeUIEvent.SearchUser -> navigation.navigateToRepositories(
                _state.value.searchAppBar
            )
        }
    }

}