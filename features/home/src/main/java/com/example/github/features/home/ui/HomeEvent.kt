package com.example.github.features.home.ui

import com.example.github.data.repo.model.User
import com.example.github.features.UIEvent

sealed class HomeEvent : UIEvent {
    data class ShowRepositories(val user: User) : HomeEvent()
}
