package com.example.github.data.repo.remote

import com.example.github.data.repo.remote.RepoConstants.PAGE_PATH
import com.example.github.data.repo.remote.RepoConstants.PER_PAGE_PATH
import com.example.github.data.repo.remote.RepoConstants.REPOSITORIES_URL
import com.example.github.data.repo.remote.RepoConstants.SINCE
import com.example.github.data.repo.remote.RepoConstants.USERS_URL
import com.example.github.data.repo.remote.RepoConstants.USER_URL
import com.example.github.data.repo.remote.model.RepositoryResponse
import com.example.github.data.repo.remote.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

internal interface RepoService {

    @GET(USERS_URL)
    @Headers(
        "Accept: application/vnd.github+json",
    )
    suspend fun users(
        @Query(SINCE) page: Long,
        @Query(PER_PAGE_PATH) perPage: Int,
    ): List<UserResponse>

    @GET(USER_URL)
    suspend fun user(
        @Path(value = "login", encoded = true) login: String,
    ): UserResponse

    @GET(REPOSITORIES_URL)
    suspend fun repositories(
        @Path(value = "user", encoded = true) user: String,
        @Query(PAGE_PATH) page: Int,
        @Query(PER_PAGE_PATH) perPage: Int,
    ): List<RepositoryResponse>

}

internal object RepoConstants {
    const val PAGE_PATH = "page"
    const val PER_PAGE_PATH = "per_page"
    const val SINCE = "since"

    const val USERS_URL = "users"
    const val USER_URL = "users/{login}"
    const val REPOSITORIES_URL = "/users/{user}/repos"
}