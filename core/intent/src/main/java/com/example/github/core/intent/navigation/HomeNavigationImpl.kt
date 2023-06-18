package com.example.github.core.intent.navigation

import com.example.github.core.intent.core.NavigationManager
import com.example.github.core.intent.destination.RepositoriesDestination
import com.example.github.data.repo.model.User
import com.example.github.features.home.navigation.HomeNavigation
import javax.inject.Inject

class HomeNavigationImpl @Inject constructor(
    private val navigationManager: NavigationManager
) : HomeNavigation {

    override fun navigateToRepositories(login: String) = navigationManager.navigate(
        RepositoriesDestination.Repositories.createRoute(login)
    )
}