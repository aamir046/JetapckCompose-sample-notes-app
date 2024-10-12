package com.example.jetpackcompose.ui.addnote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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

    val onAction: (AddNoteAction) -> Unit = { action ->
        viewmodel.handle(action)
    }

    Scaffold(
        topBar = {
            AddNoteAppBar(
                onBack = onback,
                onAction=onAction
            )
        }
    ) {paddingValues->
        Box(modifier = modifier
            .padding(paddingValues)
            .background(Color.Black)
        ){
            AddNoteScreenContent(modifier)
        }
    }
}

@Composable
fun AddNoteScreenContent(modifier: Modifier=Modifier){
    Column(modifier = modifier) {
        val titleState = remember { mutableStateOf("") }
        val noteBodyState = remember { mutableStateOf("") }

        TextField(
            value = titleState.value,
            onValueChange = {
                titleState.value = it
            },
            placeholder = { Text("Title", color = textFieldHintsColor, fontSize = 38.sp, fontWeight = FontWeight.SemiBold) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White,
                focusedTextColor = Color.White
            ),
            textStyle = TextStyle(
                fontSize = 38.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            ),
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        )

        TextField(
            value = noteBodyState.value,
            onValueChange = {
                noteBodyState.value = it
            },
            placeholder = { Text("Type Something...", color = textFieldHintsColor, fontSize = 20.sp, fontWeight = FontWeight.Normal) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White,
                focusedTextColor = Color.White
            ),
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White
            ),
            modifier = Modifier.padding(8.dp).fillMaxWidth()
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