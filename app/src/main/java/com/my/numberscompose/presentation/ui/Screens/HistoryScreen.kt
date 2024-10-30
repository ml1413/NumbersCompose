package com.my.numberscompose.presentation.ui.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.my.numberscompose.domain.model.ModelNumber
import com.my.numberscompose.presentation.HistoryViewModel
import com.my.numberscompose.presentation.HistoryViewModel.StateHistory.Initial
import com.my.numberscompose.presentation.HistoryViewModel.StateHistory.Result

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    historyViewModel: HistoryViewModel,
    onSearchClickListener: (String) -> Unit = {},
    onRandomButtonClickListener: () -> Unit = {},
    onItemClickListener: (ModelNumber) -> Unit = {}
) {
    val historyViewModelState = historyViewModel.history.observeAsState(Initial)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val text = remember { mutableStateOf("") }
        val isDigits = text.value.isDigitsOnly()
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = text.value,
            onValueChange = { text.value = it },
            singleLine = true,
            label = { Text("Enter num") },
            isError = isDigits.not(),
            trailingIcon = {
                IconButton(onClick = {
                    if (isDigits) onSearchClickListener(text.value)
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
        )
        ElevatedButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            onClick = onRandomButtonClickListener,
            shape = MaterialTheme.shapes.small
        ) {
            Text("Get Info About Random Num")
        }
        when (val state = historyViewModelState.value) {
            Initial -> {}
            is Result -> {
                val listModelNumber = state.listModel
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(listModelNumber) { item ->
                        MyItem(
                            modifier = modifier
                                .padding(bottom = 8.dp)
                                .clickable { onItemClickListener(item) },
                            model = item
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MyItem(modifier: Modifier = Modifier, model: ModelNumber) {
    val color = DividerDefaults.color
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = color, shape = MaterialTheme.shapes.small),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = model.number,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.headlineLarge
        )
        Divider(modifier = modifier.padding(vertical = 16.dp), thickness = 1.dp, color = color)
        Text(
            modifier = modifier.padding(horizontal = 16.dp),
            maxLines = 1,
            text = model.info
        )
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview(modifier: Modifier = Modifier) {
    val model = ModelNumber(info = "fdsfdfsdfdsfdsf", number = "23")
    MaterialTheme {
        MyItem(model = model)
    }
}
