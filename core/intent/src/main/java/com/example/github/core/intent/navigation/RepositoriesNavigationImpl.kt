package com.example.github.core.intent.navigation

import com.example.github.core.intent.core.NavigationManager
import com.example.github.features.repo.navigation.RepositoriesNavigation
import javax.inject.Inject

class RepositoriesNavigationImpl @Inject constructor(
    private val navigationManager: NavigationManager
) : RepositoriesNavigation {

    override fun popBackStack() = navigationManager.popBackStack()

}