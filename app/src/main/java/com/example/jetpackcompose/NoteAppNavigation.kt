package com.example.jetpackcompose

import androidx.navigation.NavHostController
import com.example.jetpackcompose.NotesAppArgs.USER_MESSAGE_ARG
import com.example.jetpackcompose.NotesAppArgs.USER_NOTE_ARG
import com.example.jetpackcompose.NotesAppDestinations.NOTE_DETAILS_ROUTE
import com.example.jetpackcompose.NotesAppScreens.ADD_EDIT_NOTE_SCREEN
import com.example.jetpackcompose.NotesAppScreens.HOME_SCREEN
import com.example.jetpackcompose.NotesAppScreens.NOTE_DETAILS_SCREEN
import com.example.jetpackcompose.data.model.Note
import com.google.gson.Gson

/**
 * Screens used in [NotesAppDestinations]
 */
private object NotesAppScreens {
    const val HOME_SCREEN = "home"
    const val ADD_EDIT_NOTE_SCREEN = "addTask"
    const val NOTE_DETAILS_SCREEN = "noteDetails"
}

/**
 * Arguments used in [NotesAppDestinations] routes
 */
object NotesAppArgs {
    const val USER_MESSAGE_ARG = "userMessage"
    const val USER_NOTE_ARG = "userNote"
}

/**
 * Destinations used in the [MainActivity]
 */
object NotesAppDestinations {
    const val HOME_ROUTE = "$HOME_SCREEN?$USER_MESSAGE_ARG={$USER_MESSAGE_ARG}"
    const val ADD_NOTE_ROUTE = "$ADD_EDIT_NOTE_SCREEN?$USER_NOTE_ARG={$USER_NOTE_ARG}"
    const val NOTE_DETAILS_ROUTE = "$NOTE_DETAILS_SCREEN/{$USER_NOTE_ARG}"
}

/**
 * Models the navigation actions in the app.
 */
class NoteAppNavigation(private val navController: NavHostController) {

    fun navigateToAddEditNote(note:Note?=null) {

        val gson = Gson()
        val jsonString = gson.toJson(note)

        navController.navigate(
            ADD_EDIT_NOTE_SCREEN.let {
                if (note != null) {
                    "$it?$USER_NOTE_ARG=$jsonString"
                } else {
                    it
                }
            }
        )
    }

    fun navigateToNoteDetails(note:Note) {
        val gson = Gson()
        val jsonString = gson.toJson(note)
        navController.navigate(
            NOTE_DETAILS_ROUTE.replace("{$USER_NOTE_ARG}", jsonString)
        )
    }
}
