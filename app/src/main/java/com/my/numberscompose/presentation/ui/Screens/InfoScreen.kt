package com.my.numberscompose.presentation.ui.Screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.my.numberscompose.domain.model.ModelNumber
import com.my.numberscompose.presentation.ModelNumberViewModel
import com.my.numberscompose.presentation.ModelNumberViewModel.State.Error
import com.my.numberscompose.presentation.ModelNumberViewModel.State.Initial
import com.my.numberscompose.presentation.ModelNumberViewModel.State.Loading
import com.my.numberscompose.presentation.ModelNumberViewModel.State.Result
import com.my.numberscompose.presentation.ModelNumberViewModel.State.FromItem

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    modelNumberViewModel: ModelNumberViewModel,
    onLoadingListener: (Boolean) -> Unit,
    onErrorListener: (String) -> Unit,
    onResultListener: (ModelNumber) -> Unit
) {
    val modelNumberViewModelState =
        modelNumberViewModel.numberModelValue.observeAsState(Initial)
    when (val state = modelNumberViewModelState.value) {
        Initial -> {}
        is Loading -> onLoadingListener(state.isLoading)

        is Error -> onErrorListener(state.message)

        is Result -> {
            val modelNumber = state.modelNumber
            onResultListener(modelNumber)
            ScreenContent(modifier, modelNumber)
        }

        is FromItem -> {
            val modelNumber = state.modelNumber
            ScreenContent(modifier, modelNumber)
        }
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier,
    modelNumber: ModelNumber
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = modelNumber.number,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.headlineLarge
        )
        Divider(modifier = modifier.padding(vertical = 16.dp), thickness = 4.dp)
        Text(
            modifier = modifier.verticalScroll(state = rememberScrollState()),
            text = modelNumber.info
        )
    }
}
