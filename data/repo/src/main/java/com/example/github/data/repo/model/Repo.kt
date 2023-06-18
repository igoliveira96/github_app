package com.example.github.data.repo.model

data class Repo(
    val name: String,
    val description: String?,
    val repoURL: String,
    val stargazersCount: Int,
    val language: String?,
    val createdAt: String
) {

    companion object {
        val EXAMPLE = Repo(
            name = "torvalds/libdc-for-dirk",
            description = "Only use for syncing with Dirk, don't use for anything else",
            repoURL = "https://github.com/torvalds/libdc-for-dirk",
            stargazersCount = 192,
            language = "C",
            createdAt = "2009-01-21T20:45:27Z"
        )
    }

}
