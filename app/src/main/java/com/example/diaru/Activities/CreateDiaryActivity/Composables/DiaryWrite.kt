package com.example.diaru.Activities.CreateDiaryActivity.Composables

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaru.Activities.CreateDiaryActivity.DiaryCreateViewModel
import com.example.diaru.Activities.CreateDiaryActivity.UI_STATES
import com.example.diaru.Activities.MainActivity.MainActivity
import com.example.diaru.R
import com.example.diaru.Settings.SettingsHandler
import com.example.diaru.database.diary.DiaryEntity
import com.example.diaru.database.diary.DiaryViewModel
import com.example.diaru.ui.theme.darkSkyBlue
import com.example.diaru.ui.theme.notepadYellow


// This is also being used by the diary view and the diary edit
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryWriteEntry(
    diaryCreateViewModel: DiaryCreateViewModel, context: Context
    // ↓ This is being used by the DiaryViewActivity.kt file
    , diaryEntity: DiaryEntity? = null, edit: Boolean = false, diaryViewModel: DiaryViewModel? = null
) {
    val activity: Activity = context as Activity
    activity.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

    var textState = remember { derivedStateOf { TextFieldValue(diaryCreateViewModel.content.value) } }
    var readOnly by remember { mutableStateOf(false) }

    if (diaryEntity != null) {
        readOnly = true
        diaryCreateViewModel.customDate.value = diaryEntity.date
    }
    if (edit) {
        readOnly = false
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

    Column {

        if (settings.getSettingBoolean("preference_wordcount", default = true, context)) {
            Text(
                "${textState.value.text.trim().split("\\s+".toRegex()).size} words",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            )
        }


        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {

            BasicTextField(
                value = textState.value.text,
                readOnly = readOnly,
                onValueChange = { diaryCreateViewModel.content.value = it },
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
                    if (textState.value.text.isNotEmpty()) {
                        if (edit) {
                            diaryViewModel?.let {
                                Toast.makeText(context, "Editing diary entry", Toast.LENGTH_SHORT).show()
                                diaryEntity!!.content = textState.value.text
                                it.update(diaryEntity)
                                val intent: Intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                                context.startActivity(intent)
                            }
                        } else {
                            Toast.makeText(context, "Adding diary entry", Toast.LENGTH_SHORT).show()
                            diaryCreateViewModel.content.value = textState.value.text
                            diaryCreateViewModel.contentScreen.value = UI_STATES.CONTENT_ADD
                        }

                    } else {
                        Toast.makeText(context, "Please write something", Toast.LENGTH_SHORT).show()
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 5.dp, start = 8.dp, end = 8.dp),
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

}