package com.example.jetpackcompose.ui.addnote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.data.model.Note
import com.example.jetpackcompose.data.repository.addnote.AddNotesIRepoSource
import com.example.jetpackcompose.data.source.local.entity.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI State that represents HomeScreen
 **/
data class AddNoteState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val showUserMessage: String? = "",
    val note: Note = Note()
)

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val addNotesIRepoSource: AddNotesIRepoSource
) : ViewModel() {
    // UI state exposed to the UI
    private val _showMessage: MutableStateFlow<String?> = MutableStateFlow("")
    private val _errorMessage: MutableStateFlow<String?> = MutableStateFlow("")
    private val _note: MutableStateFlow< Note?> = MutableStateFlow(Note())

    val uiState: StateFlow<AddNoteState> =
        combine(_note, _showMessage, _errorMessage) { note, showMessage, errorMessage ->
            AddNoteState(
                note = note ?: Note(),
                showUserMessage = showMessage ?: "",
                errorMessage = errorMessage ?: ""
            )
        }.stateIn(viewModelScope, SharingStarted.Eagerly, AddNoteState())

    fun saveNote(note: Note) {
        viewModelScope.launch {
            addNotesIRepoSource.insertNote(
                noteEntity = NoteEntity(
                    title = note.title,
                    description = note.description,
                    color = note.color,
                    date = note.date
                )
            )
            showSnackBarMessage("Note Saved Successfully!")
        }
    }

    fun updateTitle(title: String) {
        _note.update {
            it?.copy(title = title)
        }
    }

    fun updateDescription(description: String) {
        _note.update {
            it?.copy(description = description)
        }
    }

    fun snackBarMessageShown() {
        _showMessage.value = null
    }

    fun showSnackBarMessage(message: String) {
        _showMessage.value = message
    }
}