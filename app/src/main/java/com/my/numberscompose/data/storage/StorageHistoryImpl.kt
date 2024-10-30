package com.my.numberscompose.data.storage

import androidx.lifecycle.LiveData
import com.my.numberscompose.data.storage.DB.NumberEntity
import com.my.numberscompose.presentation.App

class StorageHistoryImpl() : StorageHistory {
    //todo need delete
    val db = App.getInstance().db.getDao()


    override suspend fun addToStorage(numberEntity: NumberEntity) {
        db.add(numberEntity = numberEntity)
    }

    override fun getAllModelFromStorage(): LiveData<List<NumberEntity>> {
        return db.getAllModel()
    }
}