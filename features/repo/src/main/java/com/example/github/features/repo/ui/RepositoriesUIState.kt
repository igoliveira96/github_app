package com.example.github.features.repo.ui

import com.example.github.data.repo.model.User
import com.example.github.features.UIState

data class RepositoriesUIState(
    val user: User? = null
) : UIState
