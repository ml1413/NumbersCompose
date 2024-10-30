package com.my.numberscompose.domain.repository

import androidx.lifecycle.LiveData
import com.my.numberscompose.data.model.ModelNumberFromRetrofit
import com.my.numberscompose.data.storage.DB.NumberEntity
import com.my.numberscompose.domain.model.ModelNumber

interface RepositoryNumInfo {
   suspend fun getInfoAboutNumber(num: String): Result<ModelNumber?>
   suspend fun getInfoRandomNumber():  Result<ModelNumber?>
   suspend fun add(modelNumber: ModelNumber)
   fun getAllModel(): LiveData<List<ModelNumber>>
}