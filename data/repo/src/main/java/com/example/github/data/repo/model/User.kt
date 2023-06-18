package com.example.github.data.repo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val login: String,
    val avatarURL: String,
    val name: String? = null,
    val location: String? = null,
) : Parcelable {

    companion object {
        val EMPTY = User(
            name = null,
            location = null,
            login = "",
            avatarURL = ""
        )

        val EXAMPLE = User(
            name = "Igor Goulart",
            location = "Portland, OR",
            login = "igoliveira96",
            avatarURL = "https://avatars.githubusercontent.com/u/1?v=4"
        )
    }

}
