package com.example.jetpackcompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R

// Set of Material typography styles to start with
val Typography = Typography(

    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito_regular, FontWeight.Bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito_regular, FontWeight.Normal)),
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        letterSpacing = 0.5.sp
    ),

    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito_regular, FontWeight.Normal)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),

    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito_regular, FontWeight.Normal)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.25.sp
    ),

    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito_regular, FontWeight.Normal)),
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        letterSpacing = 0.sp
    )

)