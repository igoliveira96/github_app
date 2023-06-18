package com.example.github.features.home.ui

import com.example.github.core.uikit.UIEvent
import com.example.github.data.repo.model.User

sealed class HomeUIEvent : UIEvent {
    data class ShowRepositories(val user: User) : HomeUIEvent()
}
