package com.my.numberscompose.data.storage.DB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumberEntity::class], version = 1)
abstract class HistoryDataBase() : RoomDatabase() {
     abstract fun getDao(): HistoryDao
}