package com.example.jetpackcompose.ui.notedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.data.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class NoteDetailsState(
    val note:Note = Note(),
    val showConfirmationMessage:Boolean = false
)

@HiltViewModel
class NoteDetailsViewmodel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _uiState:MutableStateFlow<NoteDetailsState> = MutableStateFlow(NoteDetailsState())
    val uiState = _uiState.asStateFlow()

    fun updateNote(note: Note){
        _uiState.update {
            it.copy(note = note)
        }
    }
}