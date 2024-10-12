package com.example.jetpackcompose.utils

import androidx.compose.ui.graphics.Color
import com.example.jetpackcompose.ui.theme.noteCardColorGreen
import com.example.jetpackcompose.ui.theme.noteCardColorLightRed
import com.example.jetpackcompose.ui.theme.noteCardColorNavyLight
import com.example.jetpackcompose.ui.theme.noteCardColorPurple
import com.example.jetpackcompose.ui.theme.noteCardColorSkyBlue
import com.example.jetpackcompose.ui.theme.noteCardColorYellow

object Utils {

    fun <T> getColor(color:T):Color{
        return when(color){
            NotesColor.RED -> noteCardColorLightRed
            NotesColor.GREEN -> noteCardColorGreen
            NotesColor.YELLOW -> noteCardColorYellow
            NotesColor.SKYBLUE -> noteCardColorSkyBlue
            NotesColor.PURPLE -> noteCardColorPurple
            NotesColor.NAVYLIGHT -> noteCardColorNavyLight
            else -> noteCardColorLightRed
        }
    }
}