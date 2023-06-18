package com.example.github.data.repo.di

import com.example.github.core.network.core.Retrofit
import com.example.github.data.repo.datasource.UserDataSource
import com.example.github.data.repo.remote.NetworkConnection
import com.example.github.data.repo.remote.RepoService
import com.example.github.data.repo.remote.datasource.UserDataSourceImpl
import com.example.github.data.repo.remote.repository.RepoRepositoryImpl
import com.example.github.data.repo.repository.RepoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    internal fun providesMatchService(
    ): RepoService = Retrofit(
        baseUrl = NetworkConnection.BASE_URL,
    )

    @Singleton
    @Provides
    internal fun providesUserDataSource(
        userDataSourceImpl: UserDataSourceImpl
    ): UserDataSource = userDataSourceImpl

}


@Module
@InstallIn(SingletonComponent::class)
internal abstract class MatchRepositoryModule {

    @Binds
    internal abstract fun bindsRepoRepository(
        repoRepositoryImpl: RepoRepositoryImpl
    ): RepoRepository

}