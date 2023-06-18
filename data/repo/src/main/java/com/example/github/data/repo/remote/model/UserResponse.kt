package com.example.github.data.repo.remote.model

import com.google.gson.annotations.SerializedName

internal data class UserResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarURL: String
)
