package com.example.github.features.repo.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.github.data.repo.model.Repo
import com.example.github.data.repo.model.User
import com.example.github.data.repo.repository.RepoRepository
import com.example.github.features.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: RepoRepository
) : ViewModel() {

    private val _state: MutableStateFlow<RepositoriesUIState> = MutableStateFlow(RepositoriesUIState())
    val state: StateFlow<RepositoriesUIState>
        get() = _state

    init {
        viewModelScope.launch {
            delay(1_000)
            try {
                savedStateHandle.get<User>("user")?.let {
                    it.name
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        _state.update {
            it.copy(user = User.EXAMPLE)
        }
    }

    fun getRepositories(user: User): Flow<PagingData<Repo>> {
        return repository.getRepositories(user.login).cachedIn(viewModelScope)
    }

    private fun getUser(login: String) {

    }

}