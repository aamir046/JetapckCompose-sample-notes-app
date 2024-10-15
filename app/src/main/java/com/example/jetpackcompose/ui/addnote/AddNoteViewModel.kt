package com.example.jetpackcompose.ui.addnote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.data.repository.addnote.AddNotesIRepoSource
import com.example.jetpackcompose.data.source.local.entity.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val addNotesIRepoSource: AddNotesIRepoSource
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

            is AddNoteAction.SaveNote -> {
                Timber.tag("Action").e("SaveNote: ${action.title} ${action.description}")

                viewModelScope.launch {
                val notes = addNotesIRepoSource.getNotes()
                }
            }

            else -> {
                Timber.tag("Action").e("ELSE: ")
            }
        }
    }

    fun snackBarMessageShown() {
        _showMessage.value = null
    }

   private fun showSnackBarMessage(message: String) {
        _showMessage.value = message
    }
}