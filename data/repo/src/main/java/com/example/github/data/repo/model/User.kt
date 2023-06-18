package com.example.github.data.repo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long,
    val login: String,
    val avatarURL: String,
    val name: String? = null,
    val location: String? = null,
) : Parcelable {

    companion object {
        val EMPTY = User(
            id = 1,
            name = null,
            location = null,
            login = "",
            avatarURL = ""
        )

        val EXAMPLE = User(
            id = 1,
            name = "Igor Goulart",
            location = "Portland, OR",
            login = "igoliveira96",
            avatarURL = "https://avatars.githubusercontent.com/u/1?v=4"
        )
    }

}
