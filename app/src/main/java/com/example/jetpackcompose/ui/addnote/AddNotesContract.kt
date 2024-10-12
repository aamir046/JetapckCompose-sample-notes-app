package com.example.jetpackcompose.ui.addnote

/**
 * UI State that represents HomeScreen
 **/
data class AddNoteState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val showUserMessage: String? = ""
)

/**
 * Home Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed class AddNoteAction {
    data class ShowUserMessage(val message: String) : AddNoteAction()
}