package com.my.numberscompose.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navController: NavHostController,
    screenHistoryContent: @Composable () -> Unit,
    screenInfoContent: @Composable () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screens.HistoryScreen.route,
        builder = {
            composable(Screens.HistoryScreen.route) {
                screenHistoryContent()
            }
            composable(Screens.InfoScreen.route) {
                screenInfoContent()
            }
        }
    )
}