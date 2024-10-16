package com.example.jetpackcompose.ui.addnote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackcompose.ui.theme.textFieldHintsColor
import com.example.jetpackcompose.utils.AddNoteAppBar

@Composable
fun AddNoteScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    onback: () -> Unit = {},
    viewmodel: AddNoteViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
){
    val uiState: AddNoteState by viewmodel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AddNoteAppBar(
                onBack = onback,
                onSave = {viewmodel.saveNote(uiState.note)},
                onVisibility = {viewmodel.showSnackBarMessage("Under Development yet!")}
            )
        },
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {paddingValues->
        Box(modifier = modifier
            .padding(paddingValues)
            .background(Color.Black)
        ){
            AddNoteScreenContent(
                title = uiState.note.title,
                onUpdateTitle = viewmodel::updateTitle,
                description = uiState.note.description,
                onUpdateDescription = viewmodel::updateDescription
            )
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
fun AddNoteScreenContent(
    modifier: Modifier=Modifier,
    title:String="",
    onUpdateTitle:(String)->Unit={},
    description:String="",
    onUpdateDescription:(String)->Unit={}
){
    Column(modifier = modifier) {
        TextField(
            value = title,
            onValueChange = {
                onUpdateTitle(it)
            },
            placeholder = { Text(text = "Title",
                color = textFieldHintsColor,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 38.sp),
                modifier = Modifier.fillMaxWidth()) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White,
                focusedTextColor = Color.White
            ),
            textStyle = MaterialTheme.typography.titleLarge.copy(fontSize = 38.sp),
            modifier = Modifier.fillMaxWidth().padding(all = 0.dp)
        )

        BasicTextField(
            value = description,
            onValueChange = {
                onUpdateDescription(it)
            },
            textStyle = MaterialTheme.typography.labelLarge.copy(color = Color.White),
            cursorBrush = SolidColor(Color.White),
            modifier = Modifier
                .background(Color.Transparent)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (description.isEmpty()) {
                    Text(
                        "Type Something...",
                        style = MaterialTheme.typography.labelLarge,
                        color = textFieldHintsColor,
                    )
                }
                innerTextField() // This renders the actual text input
            }
        )
    }

}

@Preview
@Composable
fun PreviewAddNoteScreen(
    modifier: Modifier = Modifier.fillMaxSize()
){
    Scaffold(
        topBar = {
            AddNoteAppBar(
                onBack = {},
            )
        }
    ) {paddingValues->
        // Screen content
        Box(modifier = modifier
            .padding(paddingValues)
            .background(Color.Black)
        ){
            AddNoteScreenContent(modifier)
        }
    }
}