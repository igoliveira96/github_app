package com.example.github.core.commons

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<T, in Params>(private val scope: CoroutineScope) {

    private val contextProvider = ThreadContextProvider()

    abstract fun run(params: Params? = null): Flow<T>

    suspend operator fun invoke(
        params: Params? = null,
        onError: ((Throwable) -> Unit) = {},
        onSuccess: (T) -> Unit = {}
    ) {
        scope.launch(contextProvider.io) {
            try {
                run(params).collect {
                    withContext(contextProvider.main) {
                        onSuccess(it)
                    }
                }
            } catch (e: Exception) {
                withContext(contextProvider.main) {
                    onError(e)
                }
            }
        }
    }

    fun cancel() = scope.coroutineContext.cancelChildren()
}