package com.example.github.core.intent.destination

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.example.github.core.intent.core.APPNavType
import com.example.github.core.intent.destination.HomeDestination.Home.toGson
import com.example.github.data.repo.model.User

object RepositoriesDestination {

    object Repositories : ChildDestination(
        parent = ParentDestination.Repositories,
        route = "home/{login}"
    ) {
        override val arguments: List<NamedNavArgument> = listOf(
            navArgument("login") {
                type =  NavType.StringType
            }
        )

        fun createRoute(login: String): String {
            return "${parent.route}/home/$login"
        }
    }

}