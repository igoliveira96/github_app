package com.example.github.data.repo.remote.mapper

import com.example.github.core.network.mapper.BaseMapper
import com.example.github.data.repo.model.User
import com.example.github.data.repo.remote.model.UserResponse

internal object UserMapper : BaseMapper<UserResponse, User> {

    override fun toDomain(remote: UserResponse): User = User(
        login = remote.login,
        avatarURL = remote.avatarURL
    )

    override fun fromDomain(domain: User): UserResponse = UserResponse(
        login = domain.login,
        avatarURL = domain.avatarURL
    )
}