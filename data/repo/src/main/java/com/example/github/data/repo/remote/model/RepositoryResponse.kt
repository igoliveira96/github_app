package com.example.github.data.repo.remote.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("full_name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("html_url")
    val repoURL: String,
    @SerializedName("language")
    val language: String?,
    @SerializedName("created_at")
    val createdAt: String
)
