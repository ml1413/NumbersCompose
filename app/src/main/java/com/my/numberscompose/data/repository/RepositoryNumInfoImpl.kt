package com.my.numberscompose.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.my.numberscompose.data.apiInterface.ApiInterface
import com.my.numberscompose.data.model.mapToModel
import com.my.numberscompose.data.storage.DB.mapToModel
import com.my.numberscompose.data.storage.StorageHistory
import com.my.numberscompose.data.storage.StorageHistoryImpl
import com.my.numberscompose.domain.model.ModelNumber
import com.my.numberscompose.domain.model.mapToEntity
import com.my.numberscompose.domain.repository.RepositoryNumInfo
import com.my.numberscompose.presentation.App

class RepositoryNumInfoImpl() : RepositoryNumInfo {
    // todo need delete
    val storage: StorageHistory = StorageHistoryImpl()
    val retrofit = App.getInstance().retrofit
    val api = retrofit.create(ApiInterface::class.java)

    override suspend fun getInfoAboutNumber(num: String): Result<ModelNumber> {
        return getResult(num)
    }

    override suspend fun getInfoRandomNumber(): Result<ModelNumber> {
        return getResult()
    }

    private suspend fun getResult(num: String = ""): Result<ModelNumber> {
        return try {
            val response =
                if (num.isBlank())
                    api.getRandomInfo()
                else
                    api.getInfoNumber(num = num)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val model = body.mapToModel()
                Result.success(model)
            } else {
                Result.failure(Throwable(message = response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun add(modelNumber: ModelNumber) {
        storage.addToStorage(numberEntity = modelNumber.mapToEntity())
    }

    override fun getAllModel(): LiveData<List<ModelNumber>> {
        return storage.getAllModelFromStorage().map { list -> list.map { it.mapToModel() } }
    }

}