package com.example.github.core.intent.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.github.core.intent.destination.HomeDestination
import com.example.github.core.intent.destination.ParentDestination
import com.example.github.core.intent.destination.RepositoriesDestination
import com.example.github.features.home.ui.HomeScreen
import com.example.github.features.repo.ui.RepositoriesScreen

fun NavGraphBuilder.addHomeNavGraph() {
    navigation(
        route = ParentDestination.Home.route,
        startDestination = HomeDestination.Home.createRoute()
    ) {
        addHome()
    }
}

private fun NavGraphBuilder.addHome() {
    composable(HomeDestination.Home.createRoute()) {
        HomeScreen(viewModel = hiltViewModel())
    }

    composable(RepositoriesDestination.Repositories.createRoute()) {
        RepositoriesScreen(viewModel = hiltViewModel())
    }
}