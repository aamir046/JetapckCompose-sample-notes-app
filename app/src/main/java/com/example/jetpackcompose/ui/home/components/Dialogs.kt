package com.example.jetpackcompose.ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.ui.theme.btnColors

@Composable
fun AboutAppDialog(
    onDismiss: () -> Unit={},
) {

    AlertDialog(
        onDismissRequest = {  },
        containerColor = Color.Gray,
        title = {
            Text(modifier = Modifier.fillMaxWidth(),
                text = "About App",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                textAlign = TextAlign.Center,) },
        text = {
            val about = "This app is demo for Jetpack Compose and its components using MVVM architecture showcasing best practices."
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = about,
                style = MaterialTheme.typography.labelLarge,
                color = Color.White)
        },
        confirmButton = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = btnColors)
            ) {
                Text("Okay", style = MaterialTheme.typography.labelMedium,color = Color.White)
            }
        }
    )
}

@Preview
@Composable
fun PreviewAboutAppDialog() {
    AboutAppDialog()
}