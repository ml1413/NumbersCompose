package com.my.numberscompose.domain.usecase

import androidx.lifecycle.LiveData
import com.my.numberscompose.data.repository.RepositoryNumInfoImpl
import com.my.numberscompose.data.storage.DB.NumberEntity
import com.my.numberscompose.domain.model.ModelNumber
import com.my.numberscompose.domain.repository.RepositoryNumInfo

class GetAllModelFromStorageUseCase {
    // todo need delete
    val repo: RepositoryNumInfo = RepositoryNumInfoImpl()
    operator fun invoke(): LiveData<List<ModelNumber>> {
        return repo.getAllModel()
    }
}