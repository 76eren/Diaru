package com.example.diaru.Activities.CreateDiaryActivity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Content(diaryCreateViewModel: DiaryCreateViewModel) {
    InputField(diaryCreateViewModel)
}

@Composable
fun InputField(diaryCreateViewModel: DiaryCreateViewModel) {
    var value by remember { diaryCreateViewModel.title }

    Column {
        val rainbowColors: List<Color> = listOf(Color.Red, Color(0xFFFFA500), Color.Yellow, Color.Green, Color.Blue, Color(0xFF4B0082), Color(0xFF8A2BE2))

        val brush = remember {
            Brush.linearGradient(
                colors = rainbowColors
            )
        }
        TextField(
            value = value,
            onValueChange = { value = it },
            textStyle = TextStyle(brush = brush, fontSize = 28.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()

        )
    }



}