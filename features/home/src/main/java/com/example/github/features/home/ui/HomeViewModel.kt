package com.example.github.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.github.data.repo.model.User
import com.example.github.data.repo.repository.RepoRepository
import com.example.github.features.home.navigation.HomeNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigation: HomeNavigation,
    private val repository: RepoRepository
) : ViewModel() {

    fun getUsers(): Flow<PagingData<User>> {
        return repository.getUsers().cachedIn(viewModelScope)
    }

    fun sendEvent(event: HomeUIEvent) {
        when(event) {
            is HomeUIEvent.ShowRepositories -> navigation.navigateToRepositories(
                event.user.login
            )
        }
    }

}