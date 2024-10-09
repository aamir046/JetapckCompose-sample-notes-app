package com.example.jetpackcompose.utils


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.ui.home.HomeAction

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



@Preview(showBackground = true)
@Composable
fun HomeAppBarPreview(){
    HomeAppBar(onAction = {})
}