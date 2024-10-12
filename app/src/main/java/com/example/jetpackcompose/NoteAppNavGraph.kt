package com.example.jetpackcompose

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
import com.example.jetpackcompose.ui.addnote.AddNoteScreen
import com.example.jetpackcompose.ui.home.HomeScreen
import kotlinx.coroutines.CoroutineScope

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
        ) { entry ->
            HomeScreen(
                onAddTask = {navActions.navigateToAddEditTask()}
            )
        }
        composable(
            NotesAppDestinations.ADD_TASK_ROUTE
        ) { entry ->
           AddNoteScreen(
               onback = { navController.popBackStack() }
           )
        }
    }
}
