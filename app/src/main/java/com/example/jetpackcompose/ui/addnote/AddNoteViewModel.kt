package com.example.jetpackcompose.ui.addnote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

}