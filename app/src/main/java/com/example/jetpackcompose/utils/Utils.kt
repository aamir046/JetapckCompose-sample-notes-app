package com.example.jetpackcompose.utils

import androidx.compose.ui.graphics.Color
import com.example.jetpackcompose.ui.theme.noteCardColorGreen
import com.example.jetpackcompose.ui.theme.noteCardColorLightRed
import com.example.jetpackcompose.ui.theme.noteCardColorNavyLight
import com.example.jetpackcompose.ui.theme.noteCardColorPurple
import com.example.jetpackcompose.ui.theme.noteCardColorSkyBlue
import com.example.jetpackcompose.ui.theme.noteCardColorYellow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {

    fun <T> getColor(color:T):Color{
        return when(color){
            NotesColor.RED.name -> noteCardColorLightRed
            NotesColor.GREEN.name -> noteCardColorGreen
            NotesColor.YELLOW.name -> noteCardColorYellow
            NotesColor.SKYBLUE.name -> noteCardColorSkyBlue
            NotesColor.PURPLE.name -> noteCardColorPurple
            NotesColor.NAVYLIGHT.name -> noteCardColorNavyLight
            else -> noteCardColorLightRed
        }
    }

    fun getCurrentDateTime():String{
        val currentDateTime = Date()
        val formatter = SimpleDateFormat("MM-dd-yyyy : HH:mm", Locale.getDefault())
        return formatter.format(currentDateTime)
    }
}