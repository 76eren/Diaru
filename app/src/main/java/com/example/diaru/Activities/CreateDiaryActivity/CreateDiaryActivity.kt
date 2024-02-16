package com.example.diaru.Activities.CreateDiaryActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat.startActivity
import com.example.diaru.Activities.MainActivity.MainActivity
import com.example.diaru.Settings.SettingsHandler
import com.example.diaru.database.diary.DiaryEntity
import com.example.diaru.database.diary.DiaryViewModel
import com.example.diaru.ui.theme.DiaruTheme
import com.example.diaru.ui.theme.skyeBlue
import java.util.*

class CreateDiaryActivity : ComponentActivity() {
    private val diaryViewModel: DiaryViewModel by viewModels()
    private val diaryCreateViewModel: DiaryCreateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiaruTheme {
                val color = SettingsHandler().getColor(this)

                Surface(modifier = Modifier.fillMaxSize(), color = color) {
                    CreateDiaryContent(diaryCreateViewModel, this, diaryViewModel)
                }
            }
        }
    }
}

@Composable
fun CreateDiaryContent(diaryCreateViewModel: DiaryCreateViewModel, context: Context, diaryViewModel: DiaryViewModel) {
    when (diaryCreateViewModel.contentScreen.value) {
        UI_STATES.FEELING_SELECTION -> FeelingBar(diaryCreateViewModel)
        UI_STATES.TITLE_INPUT -> Content(diaryCreateViewModel, context = context)
        UI_STATES.CONTENT_WRITE -> DiaryWriteEntry(diaryCreateViewModel, context, diaryViewModel = null)
        UI_STATES.CONTENT_ADD -> loading(diaryViewModel, diaryCreateViewModel, context)
    }
}

fun loading(diaryViewModel: DiaryViewModel, diaryCreateViewModel: DiaryCreateViewModel, context: Context) {
    val entry: DiaryEntity = DiaryEntity(
        UUID.randomUUID().toString(),
        diaryCreateViewModel.content.value,
        diaryCreateViewModel.customDate.value,
        diaryCreateViewModel.feeling.intValue,
        diaryCreateViewModel.title.value
    )

    diaryViewModel.insert(entry)

    val intent: Intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}





