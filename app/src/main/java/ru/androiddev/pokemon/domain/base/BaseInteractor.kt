package ru.androiddev.pokemon.domain.base

import kotlinx.coroutines.*

abstract class BaseInteractor<T, in Params> {

    private var backgroundContext = Dispatchers.IO

    protected abstract suspend fun executeOnBackground(params: Params): T

    suspend fun execute(
        params: Params,
        onComplete: ((T) -> Unit),
        onError: ((error: Throwable) -> Unit)
    ) {
        try {
            val result = withContext(backgroundContext) {
                executeOnBackground(params)
            }
            onComplete.invoke(result)
        } catch (e: Exception) {
            onError.invoke(e)
        }
    }
}