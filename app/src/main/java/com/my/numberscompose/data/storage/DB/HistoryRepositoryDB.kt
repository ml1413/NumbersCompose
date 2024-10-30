package com.my.numberscompose.data.storage.DB

import javax.inject.Inject

class HistoryRepositoryDB @Inject constructor(private val database: HistoryDataBase) {
    fun getAll() = database.getDao().getAllModel()
    suspend fun add(numberEntity: NumberEntity) {
        database.getDao().add(numberEntity = numberEntity)
    }
}