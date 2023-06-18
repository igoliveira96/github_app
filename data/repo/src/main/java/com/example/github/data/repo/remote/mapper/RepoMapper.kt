package com.example.github.data.repo.remote.mapper

import com.example.github.core.network.mapper.BaseMapper
import com.example.github.data.repo.model.Repo
import com.example.github.data.repo.model.User
import com.example.github.data.repo.remote.model.RepositoryResponse
import com.example.github.data.repo.remote.model.UserResponse

internal object RepoMapper : BaseMapper<RepositoryResponse, Repo> {

    override fun toDomain(remote: RepositoryResponse): Repo = Repo(
        name = remote.name,
        description = remote.description,
        repoURL = remote.repoURL,
        stargazersCount = remote.stargazersCount,
        language = remote.language,
        createdAt = remote.createdAt
    )

    override fun fromDomain(domain: Repo): RepositoryResponse = RepositoryResponse(
        name = domain.name,
        description = domain.description,
        repoURL = domain.repoURL,
        stargazersCount = domain.stargazersCount,
        language = domain.language,
        createdAt = domain.createdAt
    )
}