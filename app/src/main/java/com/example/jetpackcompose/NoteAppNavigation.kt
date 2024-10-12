package com.example.jetpackcompose

import androidx.navigation.NavHostController
import com.example.jetpackcompose.NotesAppDestinations.ADD_TASK_ROUTE
import com.example.jetpackcompose.NotesAppArgs.USER_MESSAGE_ARG
import com.example.jetpackcompose.NotesAppScreens.ADD_EDIT_TASK_SCREEN
import com.example.jetpackcompose.NotesAppScreens.HOME_SCREEN

/**
 * Screens used in [NotesAppDestinations]
 */
private object NotesAppScreens {
    const val HOME_SCREEN = "home"
    const val ADD_EDIT_TASK_SCREEN = "addTask"
}

/**
 * Arguments used in [NotesAppDestinations] routes
 */
object NotesAppArgs {
    const val USER_MESSAGE_ARG = "userMessage"
}

/**
 * Destinations used in the [MainActivity]
 */
object NotesAppDestinations {
    const val HOME_ROUTE = "$HOME_SCREEN?$USER_MESSAGE_ARG={$USER_MESSAGE_ARG}"
    const val ADD_TASK_ROUTE = ADD_EDIT_TASK_SCREEN
}

/**
 * Models the navigation actions in the app.
 */
class NoteAppNavigation(private val navController: NavHostController) {

    fun navigateToAddEditTask() {
        navController.navigate(
            ADD_TASK_ROUTE
        )
    }
}
