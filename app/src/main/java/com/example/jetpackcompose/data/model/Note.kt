package com.example.jetpackcompose.data.model

import com.example.jetpackcompose.utils.NotesColor

data class Note (
    val title :String = "",
    val description :String = "",
    val date :String = "1-1-1002",
    val color:String = NotesColor.RED.name
)