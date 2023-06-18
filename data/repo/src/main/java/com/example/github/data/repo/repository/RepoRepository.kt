package com.example.github.data.repo.repository

import androidx.paging.PagingData
import com.example.github.data.repo.model.Repo
import com.example.github.data.repo.model.User
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    fun getUsers(): Flow<PagingData<User>>

    fun getUser(login: String): Flow<User>

    fun getRepositories(user: String): Flow<PagingData<Repo>>

}