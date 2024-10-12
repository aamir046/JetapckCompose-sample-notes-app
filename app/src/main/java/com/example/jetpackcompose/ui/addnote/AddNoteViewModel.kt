package com.example.jetpackcompose.ui.addnote

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class AddNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    // UI state exposed to the UI
    private val _showMessage: MutableStateFlow<String?> = MutableStateFlow("")
    private val _errorMessage: MutableStateFlow<String?> = MutableStateFlow("")

    val uiState: StateFlow<AddNoteState> = combine(_showMessage,_errorMessage){showMessage,errorMessage->
        AddNoteState(
            showUserMessage = showMessage?:"",
            errorMessage = errorMessage?:""
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, AddNoteState())

    fun handle(action: AddNoteAction) {
        when (action) {

            is AddNoteAction.ShowUserMessage -> {
                showSnackBarMessage(action.message)
            }

            else -> {
                Timber.tag("Action").e("ELSE: ")
            }
        }
    }

    fun snackBarMessageShown() {
        _showMessage.value = null
    }

    fun showSnackBarMessage(message: String) {
        _showMessage.value = message
    }
}