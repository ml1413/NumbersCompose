package com.my.numberscompose.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.my.numberscompose.presentation.ui.Screens.HistoryScreen
import com.my.numberscompose.presentation.ui.Screens.InfoScreen
import com.my.numberscompose.presentation.ui.navigation.AppNavGraph
import com.my.numberscompose.presentation.ui.navigation.Screens
import com.my.numberscompose.presentation.ui.theme.NumbersComposeTheme
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val historyViewModel: HistoryViewModel by viewModels()
        val modelNumberViewModel: ModelNumberViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            NumbersComposeTheme {
                val isShowProgress = remember { mutableStateOf(false) }
                val navHostController = rememberNavController()
                val snackBarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = { MyAppBar(navHostController) },
                    snackbarHost = { SnackbarHost(snackBarHostState) })
                { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                        ) {
                            if (isShowProgress.value) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                        }
                        AppNavGraph(
                            navController = navHostController,
                            screenHistoryContent = {
                                HistoryScreen(
                                    historyViewModel = historyViewModel,
                                    onRandomButtonClickListener = {
                                        modelNumberViewModel.getInfoFromRandomNumber()
                                        navHostController.navigate(Screens.InfoScreen.route)
                                    },
                                    onSearchClickListener = { num ->
                                        modelNumberViewModel.getInfoAboutNumber(num = num)
                                        navHostController.navigate(Screens.InfoScreen.route)
                                    },
                                    onItemClickListener = { modelNumber ->
                                        modelNumberViewModel.setItemInVewModel(modelNumber = modelNumber)
                                        navHostController.navigate(Screens.InfoScreen.route)
                                    }
                                )
                            },
                            screenInfoContent = {
                                InfoScreen(
                                    modelNumberViewModel = modelNumberViewModel,
                                    onLoadingListener = {
                                        isShowProgress.value = it
                                    }, onErrorListener = { message ->
                                        scope.launch {
                                            Log.d("TAG1", "onCreate: ")
                                            snackBarHostState.showSnackbar(
                                                message = message,
                                                actionLabel = "OK"
                                            )
                                        }
                                    },
                                    onResultListener = { modelNumber ->
                                        historyViewModel.addToStorage(modelNumber = modelNumber)

                                    }
                                )
                            }
                        )
                    }

                }
            }

        }
    }

    @Composable
    private fun MyAppBar(navHostController: NavHostController) {
        Row(Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    if (navHostController.previousBackStackEntry != null) {
                        navHostController.popBackStack()
                    } else {
                        exitProcess(0)
                    }
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    }
}

