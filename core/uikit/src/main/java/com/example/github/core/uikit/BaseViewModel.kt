package com.example.github.core.uikit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S: UIState, E: UIEvent> : ViewModel() {

    protected abstract val _state: MutableStateFlow<S>
    abstract val state: StateFlow<S>

    abstract fun sendEvent(event: E)

}