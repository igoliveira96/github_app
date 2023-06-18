package com.example.github_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.github.core.intent.core.NavigationManager
import com.example.github.core.intent.graph.AppNavigation
import com.example.github.core.uikit.theme.GithubTheme.colors
import com.example.github.core.uikit.theme.GithubTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            GithubTheme {
                Surface(
                    color = colors.backgroundColor,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    AppNavigation(navController = navController, navManager = navManager)
                }
            }
        }
    }

}