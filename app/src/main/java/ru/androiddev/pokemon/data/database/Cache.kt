package ru.androiddev.pokemon.data.database

import kotlinx.coroutines.flow.Flow

interface Cache<T> {

    fun insert(obj: T): Flow<Boolean>

    fun insertAll(list: List<T>): Flow<Boolean>

    fun getAll(): Flow<List<T>>

    fun clearAll(): Flow<Boolean>
}