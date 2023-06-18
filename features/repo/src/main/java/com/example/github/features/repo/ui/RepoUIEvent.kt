package com.example.github.features.repo.ui

import com.example.github.core.uikit.UIEvent

sealed class RepoUIEvent : UIEvent {

    object PopBackStack : RepoUIEvent()

}
