package com.my.numberscompose.presentation.ui.navigation

sealed class Screens(val route: String) {
    object HistoryScreen : Screens(route = ROUTE_HISTORY)
    object InfoScreen : Screens(route = ROUTE_INFO)
    private companion object {
        const val ROUTE_HISTORY = "history"
        const val ROUTE_INFO = "info"
    }
}