package com.example.jetpackcompose.ui.home

import com.example.jetpackcompose.ui.home.model.Notes


/**
 * UI State that represents HomeScreen
 **/
data class HomeState(
    val notes: ArrayList<Notes> = arrayListOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val showUserMessage: String? = ""
)

/**
 * Home Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed class HomeAction {
    data class ShowUserMessage(val message: String) : HomeAction()
    data object AddNote : HomeAction()
    data object Search : HomeAction()
    data object Info : HomeAction()
}


