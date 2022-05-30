package ru.androiddev.pokemon.domain.base

import kotlinx.coroutines.*

abstract class BaseInteractor<T, in Params> {

    private var parentJob = Job()
    private var backgroundContext = Dispatchers.IO
    private var foregroundContext = Dispatchers.Main

    protected abstract suspend fun executeOnBackground(params: Params): T

    fun execute(
        params: Params,
        onComplete: ((T) -> Unit),
        onError: ((error: Throwable) -> Unit)
    ) {
        CoroutineScope(parentJob + foregroundContext).launch {
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

    fun isActive() = parentJob.isActive

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}