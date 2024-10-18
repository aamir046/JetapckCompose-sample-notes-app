package com.example.jetpackcompose.data.model

import android.os.Parcelable
import com.example.jetpackcompose.utils.NotesColor
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note (
    val id:Long = 0,
    val title :String = "",
    val description :String = "",
    val date :String = "",
    val color:String = NotesColor.RED.name
):Parcelable