package com.example.jetpackcompose.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.data.model.Note
import com.example.jetpackcompose.data.repository.noteshome.NotesHomeIRepoSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * UI State that represents HomeScreen
 **/
data class HomeState(
    val notes: ArrayList<Note> = arrayListOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val showUserMessage: String? = ""
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val notesHomeIRepoSource: NotesHomeIRepoSource
) : ViewModel() {
    // UI state exposed to the UI
    private val _showMessage: MutableStateFlow<String?> = MutableStateFlow("")
    private val _userNotes: MutableStateFlow<ArrayList<Note>> = MutableStateFlow(arrayListOf())

    val uiState: StateFlow<HomeState> = combine(_showMessage,_userNotes){ showMessage, userNotes ->
        HomeState(notes = userNotes, showUserMessage = showMessage?:"")
    }.stateIn(viewModelScope, SharingStarted.Eagerly, HomeState())

    private val _uistate = MutableStateFlow(HomeState())

    init {
        getNotes()
    }

    private fun getNotes() {
        //        val notes = arrayListOf<Note>()
//        notes.add(Note("Note 1", description = "Note1 Description here", color = NotesColor.RED.name))
//        notes.add(Note("Note 2", description = "Note2 Description here", color = NotesColor.PURPLE.name))
//        notes.add(Note("Note 3", description = "Note3 Description here", color = NotesColor.SKYBLUE.name))
//        _userNotes.value = notes
        viewModelScope.launch {
            notesHomeIRepoSource.getNotes().collect { notes ->
                // Create a new list instead of mutating the old one
                _userNotes.value = ArrayList(
                    notes.map { noteEntity ->
                        Note(
                            title = noteEntity.title,
                            description = noteEntity.description,
                            color = noteEntity.color,
                            date = noteEntity.date
                        )
                    }
                )
            }
        }
    }


    fun showInfo(){
        showSnackBarMessage("INFO: ")
        Timber.tag("ClickAction").e("INFO: ")
    }

    fun showSearch(){
        showSnackBarMessage("SEARCH: ")
        Timber.tag("ClickAction").e("SEARCH: ")
    }

    fun snackBarMessageShown() {
        _showMessage.value = null
    }

    fun showSnackBarMessage(message: String) {
        _showMessage.value = message
        uiState
    }

}