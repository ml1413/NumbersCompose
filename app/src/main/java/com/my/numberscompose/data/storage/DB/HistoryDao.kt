package com.my.numberscompose.data.storage.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(numberEntity: NumberEntity)

    @Query("select*from history ORDER BY id DESC")
    fun getAllModel(): LiveData<List<NumberEntity>>
}