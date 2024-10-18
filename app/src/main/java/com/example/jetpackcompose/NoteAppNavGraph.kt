package com.example.jetpackcompose

import androidx.compose.compiler.plugins.kotlin.EmptyFunctionMetrics.composable
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose.NotesAppArgs.USER_MESSAGE_ARG
import com.example.jetpackcompose.NotesAppArgs.USER_NOTE_ARG
import com.example.jetpackcompose.data.model.Note
import com.example.jetpackcompose.ui.addnote.AddNoteScreen
import com.example.jetpackcompose.ui.home.HomeScreen
import com.example.jetpackcompose.ui.notedetails.NoteDetailsScreen
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import java.nio.charset.StandardCharsets

@Composable
fun NoteAppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = NotesAppDestinations.HOME_ROUTE,
    navActions: NoteAppNavigation = remember(navController) {
        NoteAppNavigation(navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            NotesAppDestinations.HOME_ROUTE,
            arguments = listOf(
                navArgument(USER_MESSAGE_ARG) { type = NavType.IntType; defaultValue = 0 }
            )
        ) { backStackEntry ->
            HomeScreen(
                onAddTask = {navActions.navigateToAddEditNote()},
                onNoteClick = {note-> navActions.navigateToNoteDetails(note)}
            )
        }

        composable(
            NotesAppDestinations.ADD_NOTE_ROUTE,
            arguments = listOf(
                navArgument(USER_NOTE_ARG) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
            var note:Note?=null
            val json = backStackEntry.arguments?.getString(USER_NOTE_ARG)
            json?.let {
                note = Gson().fromJson(json, Note::class.java)
            }
           AddNoteScreen(
               onback = { navController.popBackStack() },
               userNoteArg = note
           )
        }
        composable(
            NotesAppDestinations.NOTE_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(USER_NOTE_ARG) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString(USER_NOTE_ARG)
            val note = Gson().fromJson(json, Note::class.java)

            NoteDetailsScreen(
                onBack = { navController.popBackStack() },
                onEdit = {note-> navActions.navigateToAddEditNote(note)},
                userNoteArg = note
            )
        }
    }
}
