package com.example.jetpackcompose.utils


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.ui.theme.btnColors
import com.example.jetpackcompose.ui.theme.textFieldHintsColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    onInfo: (Boolean) -> Unit = {},
    onSearchQuery: (String) -> Unit = {},
    isSearchingEnabled:(Boolean)->Unit={}
){
    var searchQuery by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    isSearchingEnabled(isSearching)
    onSearchQuery(searchQuery)
    TopAppBar(
        title = {
            if (isSearching) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically // Aligns the cancel text with the input field
                ) {
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                        },
                        singleLine = true,
                        cursorBrush = SolidColor(Color.White),
                        textStyle = MaterialTheme.typography.labelLarge.copy(
                            textAlign = TextAlign.Start,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f) // Takes up available space before the cancel button
                            .background(Color.Gray, shape = RoundedCornerShape(size = 10.dp))
                            .border(1.dp, color = Color.White, shape = RoundedCornerShape(size = 10.dp)),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 10.dp), // Padding for both the input text and placeholder
                                contentAlignment = Alignment.CenterStart // Vertically center the text
                            ) {
                                if (searchQuery.isEmpty()) {
                                    Text(
                                        text = "Search Notes",
                                        style = MaterialTheme.typography.labelLarge,
                                        color = textFieldHintsColor,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier.padding(vertical = 13.dp)
                                    )
                                }
                                innerTextField() // The text entered inside the BasicTextField
                            }
                        }
                    )
                    Text(
                        text = "Cancel",
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable {
                                searchQuery = "" // Clear the search query
                                onSearchQuery("") // Reset the search
                                isSearching = false // Close the search mode
                            },
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            } else {
                Text(
                    text = "Notes",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        actions = {
            if(!isSearching) {
                IconButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .background(color = btnColors, shape = RoundedCornerShape(size = 10.dp))
                        .height(40.dp)
                        .width(40.dp),
                    onClick = { isSearching = true }
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }

                IconButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .background(color = btnColors, shape = RoundedCornerShape(size = 10.dp))
                        .height(40.dp)
                        .width(40.dp),
                    onClick = { onInfo.invoke(true) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_info),
                        contentDescription = "Info",
                        tint = Color.White
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteAppBar(
    onBack: () -> Unit={},
    onSave: () -> Unit={},
    onVisibility:()->Unit={}
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .background(
                        color = btnColors,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .height(40.dp)
                    .width(40.dp),
                onClick = { onBack.invoke() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        actions = {
            Row(modifier = Modifier.padding(end = 10.dp)) {
                IconButton(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .background(color = btnColors, shape = RoundedCornerShape(size = 10.dp))
                    .height(40.dp)
                    .width(40.dp),
                onClick = { onVisibility.invoke() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_visibility),
                    contentDescription = "show hide typing options",
                    tint = Color.White
                )
            }
                IconButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .background(color = btnColors, shape = RoundedCornerShape(size = 10.dp))
                        .height(40.dp)
                        .width(40.dp),
                    onClick = { onSave.invoke() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_save),
                        contentDescription = "Save Note",
                        tint = Color.White
                    )
                }
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewAddNoteAppBar(){
    Scaffold(){paddingvalues->
        Box(modifier = Modifier.padding(paddingvalues))
            AddNoteAppBar()
    }
}


@Preview(showBackground = true)
@Composable
fun HomeAppBarPreview(){
    Scaffold(){paddingvalues->
        Box(modifier = Modifier.padding(paddingvalues))
        HomeAppBar()
    }
}