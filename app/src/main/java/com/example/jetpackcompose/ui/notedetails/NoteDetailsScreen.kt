package com.example.jetpackcompose.ui.notedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackcompose.data.model.Note
import com.example.jetpackcompose.ui.theme.textFieldHintsColor
import com.example.jetpackcompose.utils.NoteDetailsAppBar
import timber.log.Timber

@Composable
fun NoteDetailsScreen(
    modifier: Modifier=Modifier.fillMaxSize(),
    viewmodel: NoteDetailsViewmodel= hiltViewModel(),
    onBack:()->Unit={},
    onEdit:(Note)->Unit={},
    userNoteArg:Note?=null
){
    val uiState = viewmodel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            NoteDetailsAppBar(
                onBack = onBack,
                onEdit = {onEdit.invoke(uiState.value.note)}
            )
        },
        modifier = modifier
    ) {paddingValues ->
        Box(modifier = modifier
            .padding(paddingValues)
            .background(Color.Black)
        ){
            AddNoteDetailsScreenContent(
                title = uiState.value.note.title,
                description = uiState.value.note.description
            )
        }

        userNoteArg?.let {
            LaunchedEffect(Unit){
                viewmodel.updateNote(it)
            }
        }?:Timber.e(message = "No Note Received")
    }
}

@Composable
fun AddNoteDetailsScreenContent(
    modifier: Modifier=Modifier,
    title:String="",
    description:String="",

){
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Text(text = title,
            color = textFieldHintsColor,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 38.sp),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 16.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.labelLarge,
            color = textFieldHintsColor,
            modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth()
        )

    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailsScreenPreview(){
    Scaffold(
        topBar = {
            NoteDetailsAppBar()
        },
        modifier = Modifier.fillMaxSize()
    ){paddingValues->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.Black)
        ) {
            AddNoteDetailsScreenContent()
        }
    }

}