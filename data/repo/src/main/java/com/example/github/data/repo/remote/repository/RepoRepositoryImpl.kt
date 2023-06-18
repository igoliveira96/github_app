package com.example.github.data.repo.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.github.data.repo.datasource.RepositoriesDataSource
import com.example.github.data.repo.datasource.UserDataSource
import com.example.github.data.repo.model.Repo
import com.example.github.data.repo.model.User
import com.example.github.data.repo.remote.RepoService
import com.example.github.data.repo.remote.datasource.RepositoriesDataSourceImpl
import com.example.github.data.repo.remote.mapper.UserMapper
import com.example.github.data.repo.repository.RepoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class RepoRepositoryImpl @Inject constructor(
    private val repoService: RepoService,
    private val userDataSource: UserDataSource
) : RepoRepository {

    override fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { userDataSource }
        ).flow
    }

    override fun getUser(login: String): Flow<User> = flow {
        emit(UserMapper.toDomain(repoService.user(login)))
    }

    override fun getRepositories(user: String): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { RepositoriesDataSourceImpl(user, repoService) }
        ).flow
    }
}