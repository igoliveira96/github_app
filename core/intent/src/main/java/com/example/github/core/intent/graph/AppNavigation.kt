package com.example.github.core.intent.graph

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.github.core.intent.core.NavigationCommand
import com.example.github.core.intent.core.NavigationManager
import com.example.github.core.intent.core.NavigationType
import com.example.github.core.intent.destination.ParentDestination
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation(
    navController: NavHostController,
    navManager: NavigationManager
) {
    ObserveNavigation(navController = navController, navManager = navManager)

    NavHost(
        navController = navController,
        startDestination = ParentDestination.Home.route
    ) {
        addHomeNavGraph()
    }
}

@Composable
private fun ObserveNavigation(
    navController: NavHostController,
    navManager: NavigationManager
) {
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    LaunchedEffect(Unit) {
        navManager.navigationEvent.collectLatest { navigationEvent ->
            when (navigationEvent) {
                is NavigationCommand.Navigate -> {
                    when (navigationEvent.type) {
                        NavigationType.NavigateTo -> {
                            navController.navigate(
                                route = navigationEvent.destination,
                                navOptions = navigationEvent.navOptions
                            )
                        }
                        is NavigationType.PopUpTo -> {
                            if (navigationEvent.type.savedStateHandle != null) {
                                val (key, value) = navigationEvent.type.savedStateHandle
                                navController.previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.set(key, value)
                            }
                            navController.popBackStack(
                                route = navigationEvent.destination,
                                inclusive = navigationEvent.type.inclusive
                            )
                        }
                    }
                }
                is NavigationCommand.NavigateUp -> navController.navigateUp()
                is NavigationCommand.PopStackBack -> navController.popBackStack()
                is NavigationCommand.OnBackPressed -> dispatcher?.onBackPressed()
            }
        }
    }
}
