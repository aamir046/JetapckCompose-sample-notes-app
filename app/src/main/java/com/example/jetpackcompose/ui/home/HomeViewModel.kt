package com.example.jetpackcompose.ui.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.ui.home.model.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {



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
        notes.add(Notes("Note 1"))
        notes.add(Notes("Note 1"))
        notes.add(Notes("Note 1"))
        _userNotes.value = notes
    }

    fun handle(action: HomeAction) {
        when (action) {

            is HomeAction.ShowUserMessage -> {
                showSnackBarMessage(action.message)
            }

            HomeAction.AddNote -> {
                Log.e("ClickAction", "ADDNOTE: " )
            }
            HomeAction.Info -> {
                Log.e("ClickAction", "INFO: " )
            }
            HomeAction.Search -> {
                Log.e("ClickAction", "SEARCH: " )
            }
            else -> {
                Log.e("Action", "ELSE: " )
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