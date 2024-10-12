package com.example.jetpackcompose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackcompose.R
import com.example.jetpackcompose.ui.home.model.Notes
import com.example.jetpackcompose.ui.theme.btnColors
import com.example.jetpackcompose.utils.HomeAppBar
import com.example.jetpackcompose.utils.NotesColor
import com.example.jetpackcompose.utils.Utils

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewmodel: HomeViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onAddTask: () -> Unit = {},
) {

    val uiState: HomeState by viewmodel.uiState.collectAsStateWithLifecycle()
    val onAction: (HomeAction) -> Unit = { action ->
        viewmodel.handle(action)
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            HomeAppBar(onAction = onAction)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddTask.invoke() },
                modifier = Modifier
                    .padding(16.dp)
                    .border(1.dp, color = btnColors, shape = CircleShape),
                shape = CircleShape,
                containerColor = Color.Black,
            ) {
                Icon(
                    painterResource(
                        R.drawable.ic_add
                    ),
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.height(30.dp).width(30.dp)
                )
            }
        },
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
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
                NotesList(uiState.notes, onAction)
            }
        }
    }

    uiState.showUserMessage?.let { message ->
        if (message.isNotEmpty()) {
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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painterResource(
                R.drawable.ic_empty_notes),
                contentDescription = "Empty Notes Picture")

            Text(
                text = "No notes available",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotesList(notes: ArrayList<Notes>, onAction: (HomeAction) -> Unit) {
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
                },
                backgroundColor = Utils.getColor(notes[index].color)
            ) {
                // Card content here
                Column(
                    modifier = Modifier.padding(8.dp).fillMaxWidth() // Padding inside the card
                ) {
                    Text(text = notes[index].title,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = notes[index].description,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = notes[index].date,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

//@Preview(name = "Large Screen Preview", device = "spec:width=720dp,height=1280dp", showSystemUi = true)
//@Preview(name = "Small Screen Preview", device = "spec:width=320dp,height=480dp", showSystemUi = true)
@Preview(showBackground = true, name = "Notes List", device = Devices.PIXEL_2)
@Composable
fun NotesListPreview() {
    val notes = arrayListOf<Notes>()
    notes.add(Notes("Note 1", description = "Note1 Description here", color = NotesColor.RED, date = "12-9-2024"))
    notes.add(Notes("Note 1", description = "Note2 Description here", color = NotesColor.PURPLE, date = "12-10-2024"))
    notes.add(Notes("Note 1", description = "Note3 Description here", color = NotesColor.SKYBLUE,date = "12-11-2024"))
    Scaffold(
        topBar = { HomeAppBar(onAction = {}) },
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle the click event */ },
                modifier = Modifier
                    .padding(16.dp)
                    .border(1.dp, color = btnColors, shape = CircleShape),
                shape = CircleShape,
                containerColor = Color.Black,
            ) {
                Icon(
                    painterResource(
                        R.drawable.ic_add
                    ),
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.height(30.dp).width(30.dp)
                )
            }
        })
    { paddingValues ->
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

//@Preview(showBackground = true, name = "Empty View", device = Devices.NEXUS_5)
@Preview(showBackground = true, name = "Empty View", device = Devices.PIXEL_2)
@Composable
fun EmptyNotesPreview() {
    Scaffold(
        topBar = { HomeAppBar(onAction = {}) },
        modifier = Modifier.fillMaxSize()
    )
    { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            EmptyView()
        }
    }
}




