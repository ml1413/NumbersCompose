package com.my.numberscompose.data.storage

import androidx.lifecycle.LiveData
import com.my.numberscompose.data.storage.DB.NumberEntity

interface StorageHistory {
    suspend fun addToStorage(numberEntity: NumberEntity)
    fun getAllModelFromStorage(): LiveData<List<NumberEntity>>
}