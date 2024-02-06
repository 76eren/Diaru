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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.annotation.FontRes // If you use annotation for font resource IDs
import com.example.diaru.R


@Composable
fun DiaryWriteEntry(diaryCreateViewModel: DiaryCreateViewModel) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    val customFontFamily = FontFamily(Font(R.font.beaty_diary))

    BasicTextField(
        value = textState,
        onValueChange = { textState = it },
        textStyle = TextStyle(fontSize = 26.sp, color = Color.Black, fontFamily = customFontFamily),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(notepadYellow),
        singleLine = false,
        maxLines = Int.MAX_VALUE
    )
}
