package com.example.github.core.intent.di

import com.example.github.core.di.core.ApplicationScope
import com.example.github.core.intent.core.NavigationManager
import com.example.github.core.intent.navigation.HomeNavigationImpl
import com.example.github.core.intent.navigation.RepositoriesNavigationImpl
import com.example.github.features.home.navigation.HomeNavigation
import com.example.github.features.repo.navigation.RepositoriesNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object NavigationModule {

    @Singleton
    @Provides
    internal fun providesNavigationManager(
        @ApplicationScope applicationScope: CoroutineScope
    ): NavigationManager {
        return NavigationManager(applicationScope)
    }

    @Singleton
    @Provides
    internal fun providesHomeNavigation(
        navigation: HomeNavigationImpl
    ) : HomeNavigation = navigation

    @Singleton
    @Provides
    internal fun providesRepositoriesNavigation(
        navigation: RepositoriesNavigationImpl
    ) : RepositoriesNavigation = navigation

}