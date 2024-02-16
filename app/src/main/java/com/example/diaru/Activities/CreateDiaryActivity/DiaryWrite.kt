package com.example.diaru.Activities.CreateDiaryActivity

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaru.Activities.MainActivity.MainActivity
import com.example.diaru.R
import com.example.diaru.Settings.SettingsHandler
import com.example.diaru.database.diary.DiaryEntity
import com.example.diaru.ui.theme.darkSkyBlue
import com.example.diaru.ui.theme.notepadYellow


// This is also being used by the diary view and the diary edit
@Composable
fun DiaryWriteEntry(diaryCreateViewModel: DiaryCreateViewModel, context: Context, diaryEntity: DiaryEntity? = null) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    var read = false

    if (diaryEntity != null) {
        read = true
        textState = TextFieldValue(diaryEntity.content)
        diaryCreateViewModel.customDate.value = diaryEntity.date
    }

    val settings = SettingsHandler()

    val customFontFamily: FontFamily
    val fontSize: Int
    if (settings.getSettingBoolean("preference_cursive", false, context)) {
        customFontFamily = FontFamily(Font(R.font.beaty_diary))
        fontSize = 26
    } else {
        customFontFamily = FontFamily.Default
        fontSize = 18
    }


    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = textState,
            readOnly = read,
            onValueChange = { textState = it },
            textStyle = TextStyle(fontSize = fontSize.sp, color = Color.Black, fontFamily = customFontFamily),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(notepadYellow),
            singleLine = false,
            maxLines = Int.MAX_VALUE
        )

        Spacer(modifier = Modifier.height(5.dp))

        Button(
            onClick = {
                if (textState.text.isNotEmpty()) {
                    Toast.makeText(context, "Adding diary entry", Toast.LENGTH_SHORT).show()
                    diaryCreateViewModel.content.value = textState.text
                    diaryCreateViewModel.contentScreen.value = UI_STATES.CONTENT_ADD
                }
                else {
                    Toast.makeText(context, "Please write something", Toast.LENGTH_SHORT).show()
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp, start = 8.dp, end = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = darkSkyBlue,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            )
        ) {
            Text(
                "Continue",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
        }
    }
}