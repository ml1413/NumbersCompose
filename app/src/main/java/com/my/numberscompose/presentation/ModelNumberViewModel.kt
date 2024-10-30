package com.my.numberscompose.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.numberscompose.domain.model.ModelNumber
import com.my.numberscompose.domain.usecase.GetInfoAboutNumberUseCase
import com.my.numberscompose.domain.usecase.GetInfoRandomNumberUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ModelNumberViewModel : ViewModel() {
    // todo need delete
    val getInfoRandomNumberUseCase = GetInfoRandomNumberUseCase()
    val getInfoAboutNumberUseCase = GetInfoAboutNumberUseCase()
    private val _numberModelValue = MutableLiveData<State>(State.Initial)
    val numberModelValue: LiveData<State> = _numberModelValue

    fun getInfoAboutNumber(num: String) {
        getStateFromUseCase(num)
    }

    fun getInfoFromRandomNumber() {
        getStateFromUseCase()
    }

    fun setItemInVewModel(modelNumber: ModelNumber) {
        _numberModelValue.value = State.FromItem(modelNumber = modelNumber)
    }

    private fun getStateFromUseCase(num: String = "") {
        viewModelScope.launch() {
            _numberModelValue.value = State.Loading(isLoading = true)
            delay(555)
            val resul =
                if (num.isBlank()) getInfoRandomNumberUseCase() else getInfoAboutNumberUseCase(num = num)
            _numberModelValue.value = State.Loading(isLoading = false)
            delay(555)
            resul.onSuccess { modelNumber ->
                modelNumber?.let { _numberModelValue.value = State.Result(modelNumber = it) }
            }
                .onFailure { throwable ->

                    _numberModelValue.value =
                        State.Error(message = throwable.message ?: "error body null")
                }
        }
    }

    sealed class State() {
        object Initial : State()
        class Loading(val isLoading: Boolean) : State()
        class Result(val modelNumber: ModelNumber) : State()
        class FromItem(val modelNumber: ModelNumber) : State()
        class Error(val message: String) : State()
    }
}