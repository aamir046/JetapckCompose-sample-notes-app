package com.example.jetpackcompose.ui.home.model

import com.example.jetpackcompose.utils.NotesColor

data class Notes (
    val title :String = "",
    val description :String = "",
    val date :String = "",
    val color:NotesColor = NotesColor.RED
)