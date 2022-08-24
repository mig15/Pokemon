package ru.androiddev.pokemon.data.db

import kotlinx.coroutines.flow.Flow

abstract class BaseCacheImpl<T>(
    private val dao: BaseDao<T>,
    private val applicationDataBase: ApplicationDataBase
) : Cache<T> {

    override fun insert(obj: T): Flow<Boolean> {
        //dao.insert(obj)
        TODO()
    }

    override fun insertAll(list: List<T>): Flow<Boolean> {
        //dao.insertAll(list)
        TODO()
    }

    fun getApplicationDataBase() = applicationDataBase
}