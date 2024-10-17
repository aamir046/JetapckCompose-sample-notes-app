package com.example.jetpackcompose.ui.addnote.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.btnColors
import com.example.jetpackcompose.utils.NotesColor
import com.example.jetpackcompose.utils.Utils

@Composable
fun ColorSelectionDialog(
    onColorSelected: (String) -> Unit,
    onDismiss: () -> Unit,
    selectedColor:String = "RED"
) {
    // List of color options
    val colors = listOf(
        NotesColor.RED, NotesColor.GREEN, NotesColor.YELLOW,
        NotesColor.SKYBLUE, NotesColor.PURPLE, NotesColor.NAVYLIGHT
    )

    val clickedColor  = remember { mutableStateOf(selectedColor) }
    AlertDialog(
        onDismissRequest = {  }, //empty, no need to cancel on tap outside the alert
        containerColor = Color.Gray,
        title = {
            Text(modifier = Modifier.fillMaxWidth(),
            text = "Select Note Color",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            textAlign = TextAlign.Center,) },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                colors.forEach { color ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onColorSelected(color.name)
                                clickedColor.value = color.name
                            }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(color = Utils.getColor(color.name), shape = CircleShape)
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                            text = color.name,
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White)

                        if(clickedColor.value == color.name){
                            Checkbox(
                                checked = true,
                                onCheckedChange = {},
                                enabled = false,
                                colors = CheckboxDefaults.colors(disabledCheckedColor = Color.Transparent, checkmarkColor = Utils.getColor(color.name))
                            )
                        }

                    }
                }
            }
        },
        confirmButton = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = btnColors)
            ) {
                Text("Close", style = MaterialTheme.typography.labelMedium,color = Color.White)
            }
        }
    )
}

@Preview
@Composable
fun PreviewColorSelectionDialog() {
    ColorSelectionDialog(
        onColorSelected = {},
        onDismiss = {}
    )
}