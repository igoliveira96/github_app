package com.example.github.features.home.navigation

import com.example.github.data.repo.model.User

interface HomeNavigation {

    fun navigateToRepositories(user: User)

}