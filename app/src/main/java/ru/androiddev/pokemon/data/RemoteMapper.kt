package ru.androiddev.pokemon.data

/**
 * @param <M> remote model
 * @param <T> data model
 */

interface RemoteMapper<M, T> {

    fun mapFromRemote(remote: M): T

    fun mapToRemote(entity: T): M
}