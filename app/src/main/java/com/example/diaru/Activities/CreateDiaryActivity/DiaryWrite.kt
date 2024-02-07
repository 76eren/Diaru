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
import com.example.diaru.Settings.SettingsHandler
import com.example.diaru.ui.theme.notepadYellow


@Composable
fun DiaryWriteEntry(diaryCreateViewModel: DiaryCreateViewModel, context: Context) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    val settings = SettingsHandler()

    val customFontFamily: FontFamily
    val fontSize: Int
    if (settings.getSettingBoolean("preference_cursive", false, context)) {
        customFontFamily = FontFamily(Font(R.font.beaty_diary))
        fontSize = 26
    }
    else {
        customFontFamily = FontFamily.Default
        fontSize = 18
    }

    BasicTextField(
        value = textState,
        onValueChange = { textState = it },
        textStyle = TextStyle(fontSize = fontSize.sp, color = Color.Black, fontFamily = customFontFamily),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(notepadYellow),
        singleLine = false,
        maxLines = Int.MAX_VALUE
    )
}
