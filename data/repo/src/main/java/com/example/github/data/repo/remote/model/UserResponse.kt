package com.example.github.data.repo.remote.model

import com.google.gson.annotations.SerializedName

internal data class UserResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarURL: String,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("location")
    val location: String? = null,
)
