package com.my.numberscompose.domain.usecase

import com.my.numberscompose.data.model.ModelNumberFromRetrofit
import com.my.numberscompose.data.repository.RepositoryNumInfoImpl
import com.my.numberscompose.domain.model.ModelNumber
import com.my.numberscompose.domain.repository.RepositoryNumInfo

class GetInfoRandomNumberUseCase() {
    // todo need delete
    val repo: RepositoryNumInfo = RepositoryNumInfoImpl()
    suspend operator fun invoke(): Result<ModelNumber?> {
        return repo.getInfoRandomNumber()
    }
}