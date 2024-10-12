package com.example.jetpackcompose.ui.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.ui.home.model.Notes
import com.example.jetpackcompose.utils.NotesColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    // UI state exposed to the UI
    private val _showMessage: MutableStateFlow<String?> = MutableStateFlow("")
    private val _userNotes: MutableStateFlow<ArrayList<Notes>> = MutableStateFlow(arrayListOf())

    val uiState: StateFlow<HomeState> = combine(_showMessage,_userNotes){ showMessage, userNotes ->
        HomeState(notes = userNotes, showUserMessage = showMessage?:"")
    }.stateIn(viewModelScope, SharingStarted.Eagerly, HomeState())

    init {
        addNotes()
    }

    private fun addNotes() {
        val notes = arrayListOf<Notes>()
        notes.add(Notes("Note 1", description = "Note1 Description here", color = NotesColor.RED, date = "12-9-2024"))
        notes.add(Notes("Note 2", description = "Note2 Description here", color = NotesColor.PURPLE, date = "12-10-2024"))
        notes.add(Notes("Note 3", description = "Note3 Description here", color = NotesColor.SKYBLUE,date = "12-11-2024"))
        _userNotes.value = notes
    }

    fun handle(action: HomeAction) {
        when (action) {

            is HomeAction.ShowUserMessage -> {
                showSnackBarMessage(action.message)
            }

            HomeAction.Info -> {
                showSnackBarMessage("INFO: ")
                Timber.tag("ClickAction").e("INFO: ")
            }
            HomeAction.Search -> {
                showSnackBarMessage("SEARCH: ")
                Timber.tag("ClickAction").e("SEARCH: ")
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