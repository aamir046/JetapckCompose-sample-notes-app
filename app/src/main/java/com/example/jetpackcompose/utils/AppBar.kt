package com.example.jetpackcompose.utils


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.ui.home.HomeAction
import com.example.jetpackcompose.ui.theme.backGroundColor
import com.example.jetpackcompose.ui.theme.btnColors

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(onAction: (HomeAction) -> Unit){
          TopAppBar(
              title = { Text("Notes") },
              modifier = Modifier.fillMaxWidth(),
              colors = TopAppBarDefaults.topAppBarColors(
                  containerColor = Color.Black,
                  titleContentColor = Color.White,
                  actionIconContentColor = Color.White
              ),
              actions = {
                  IconButton(onClick = { onAction(HomeAction.Search) }) {
                      Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                  }
                  IconButton(onClick = { onAction(HomeAction.Info) }) {
                      Icon(Icons.Default.Info, contentDescription = "Info", tint = Color.White)
                  }
              }
          )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteAppBar(){
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
                onClick = { /* Handle back navigation */ }
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
                onClick = { /* Handle back navigation */ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_visibility),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
                IconButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .background(color = btnColors, shape = RoundedCornerShape(size = 10.dp))
                        .height(40.dp)
                        .width(40.dp),
                    onClick = { /* Handle back navigation */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_save),
                        contentDescription = "Back",
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
    HomeAppBar(onAction = {})
}