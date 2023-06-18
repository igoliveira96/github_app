package com.example.github.data.repo.remote.mapper

import com.example.github.core.network.mapper.BaseMapper
import com.example.github.data.repo.model.User
import com.example.github.data.repo.remote.model.UserResponse

internal object UserMapper : BaseMapper<UserResponse, User> {

    override fun toDomain(remote: UserResponse): User = User(
        id = remote.id,
        login = remote.login,
        avatarURL = remote.avatarURL,
        name = remote.name,
        location = remote.location
    )

    override fun fromDomain(domain: User): UserResponse = UserResponse(
        id = domain.id,
        login = domain.login,
        avatarURL = domain.avatarURL,
        name = domain.name,
        location = domain.location
    )
}