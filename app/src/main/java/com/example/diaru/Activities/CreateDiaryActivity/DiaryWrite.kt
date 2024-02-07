package com.example.diaru.Activities.CreateDiaryActivity

import android.content.Context
import android.preference.PreferenceManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaru.R
import com.example.diaru.ui.theme.notepadYellow


@Composable
fun DiaryWriteEntry(diaryCreateViewModel: DiaryCreateViewModel, context: Context) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context)

    val customFontFamily: FontFamily
    if (sharedpreferences.getBoolean("preference_cursive", false)) {
        customFontFamily = FontFamily(Font(R.font.beaty_diary))
    }
    else {
        customFontFamily = FontFamily.Default
    }

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