package com.my.numberscompose.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.numberscompose.domain.model.ModelNumber
import com.my.numberscompose.domain.usecase.AddToStorageUseCase
import com.my.numberscompose.domain.usecase.GetAllModelFromStorageUseCase
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {
    private val getAllModelFromStorageUseCase = GetAllModelFromStorageUseCase()
    private val addToStorageUseCase = AddToStorageUseCase()
    private val _history = MutableLiveData<StateHistory>(StateHistory.Initial)
    val history: LiveData<StateHistory> = _history
    private val observer = Observer<List<ModelNumber>> { listModels ->
        _history.value = when {
            listModels.isEmpty() -> StateHistory.Initial
            else -> StateHistory.Result(listModel = listModels)
        }
    }

    fun addToStorage(modelNumber: ModelNumber) {
        viewModelScope.launch {
            addToStorageUseCase(modelNumber = modelNumber)
        }
    }

    init {
        getAllModelFromStorageUseCase().observeForever(observer)
    }

    override fun onCleared() {
        getAllModelFromStorageUseCase().removeObserver(observer)
        super.onCleared()
    }

    sealed class StateHistory {
        object Initial : StateHistory()
        class Result(val listModel: List<ModelNumber>) : StateHistory()
    }
}