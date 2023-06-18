package com.example.github.data.repo.usecase

import com.example.github.core.commons.UseCase
import com.example.github.core.di.core.ApplicationScope
import com.example.github.data.repo.model.User
import com.example.github.data.repo.repository.RepoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    @ApplicationScope coroutineScope: CoroutineScope,
    private val repository: RepoRepository
) : UseCase<User, GetUserUseCase.Params>(coroutineScope) {

    override fun run(params: Params?): Flow<User> = when(params) {
        null -> throw Exception("Missing params")
        else -> repository.getUser(params.login)
    }

    data class Params(
        val login: String
    )

}