package ru.androiddev.pokemon.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface BaseDao<T> {

    @Insert
    fun insert(obj: T)

    @Insert
    fun insertAll(obj: List<T>)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)
}