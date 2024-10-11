package com.example.jetpackcompose.ui.home
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackcompose.ui.home.model.Notes
import com.example.jetpackcompose.utils.HomeAppBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewmodel: HomeViewModel = hiltViewModel(),
    snackbarHostState:SnackbarHostState = remember { SnackbarHostState() }
) {

    val uiState : HomeState by viewmodel.uiState.collectAsStateWithLifecycle()
    val onAction: (HomeAction) -> Unit = { action ->
        viewmodel.handle(action)
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            HomeAppBar(onAction = onAction)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAction(HomeAction.AddNote) }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        },
        modifier = modifier, snackbarHost = {SnackbarHost(snackbarHostState)}
    ) { paddingValues ->

        //Screen Content
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Black)
        ) {

            if (uiState.notes.isEmpty()) {
                // Empty View
                EmptyView()
            } else {
                // List View of Notes
                NotesList(uiState.notes,onAction)
            }
        }
    }

    uiState.showUserMessage?.let { message ->
        if(message.isNotEmpty()){
            LaunchedEffect(snackbarHostState, viewmodel, message, message) {
                snackbarHostState.showSnackbar(message)
                viewmodel.snackBarMessageShown()
            }
        }
    }
}

@Composable
fun EmptyView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No notes available", fontSize = 20.sp)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotesList(notes: ArrayList<Notes>,onAction: (HomeAction) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(notes.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp),
                    elevation = 4.dp, // Shadow effect
                    shape = RoundedCornerShape(8.dp), // Rounded corners
                    onClick = {
                        val message = "Note ${notes[index].title} clicked"
                        onAction(HomeAction.ShowUserMessage(message))
                    }
                ) {
                    // Card content here
                    Column(
                        modifier = Modifier.padding(16.dp) // Padding inside the card
                    ) {
                        Text(text = notes[index].title)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text =  notes[index].description)
                    }
                }
            }
    }
}

@Preview(showBackground = true, name = "Notes List")
@Composable
fun NotesListPreview() {
    val notes = arrayListOf<Notes>()
    notes.add(Notes("Note 1"))
    notes.add(Notes("Note 1"))
    notes.add(Notes("Note 1"))
    Scaffold(topBar = {HomeAppBar(onAction = {})}, modifier = Modifier) {paddingValues->
        Box(
            modifier = Modifier
                .padding(paddingValues) // Apply the padding from Scaffold
                .fillMaxSize()
                .background(Color.Black) // Ensure the Box takes full screen space
        ) {
            NotesList(
                notes = notes, onAction = {}
            )
        }
    }

}

@Preview(showBackground = true, name = "Empty View")
@Composable
fun EmptyNotesPreview() {
    Scaffold(topBar = {HomeAppBar(onAction = {})}, modifier = Modifier) {paddingValues->
        Box(
            modifier = Modifier
                .padding(paddingValues) // Apply the padding from Scaffold
                .fillMaxSize() // Ensure the Box takes full screen space
        ) {
            EmptyView()
        }
    }
}




