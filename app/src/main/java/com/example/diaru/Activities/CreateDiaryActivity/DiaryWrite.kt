package com.example.diaru.Activities.CreateDiaryActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaru.ui.theme.notepadYellow


@Composable
fun DiaryWriteEntry(diaryCreateViewModel: DiaryCreateViewModel) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    BasicTextField(
        value = textState,
        onValueChange = { textState = it },
        textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(notepadYellow),
        singleLine = false,
        maxLines = Int.MAX_VALUE
    )
}
