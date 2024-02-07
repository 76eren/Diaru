package com.example.diaru.Activities.CreateDiaryActivity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.diaru.Settings.SettingsHandler
import com.example.diaru.database.diary.DiaryViewModel
import com.example.diaru.ui.theme.DiaruTheme
import com.example.diaru.ui.theme.skyeBlue

class CreateDiaryActivity : ComponentActivity() {
    private val diaryViewModel: DiaryViewModel by viewModels()
    private val diaryCreateViewModel: DiaryCreateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiaruTheme {
                val settings = SettingsHandler()
                val color: Color
                if (settings.getSettingBoolean("preference_theme", false, this)) {
                    color =  MaterialTheme.colorScheme.background
                    Log.d("lol", "A")
                }
                else {
                    Log.d("lol", "B")
                    color = skyeBlue
                }

                Surface(modifier = Modifier.fillMaxSize(), color = color) {
                    CreateDiaryContent(diaryCreateViewModel, this)
                }
            }
        }
    }
}

@Composable
fun CreateDiaryContent(diaryCreateViewModel: DiaryCreateViewModel, context: Context) {
    when (diaryCreateViewModel.contentScreen.value) {
        UI_STATES.FEELING_SELECTION -> FeelingBar(diaryCreateViewModel)
        UI_STATES.TITLE_INPUT -> Content(diaryCreateViewModel, context = context)
        UI_STATES.CONTENT_WRITE -> DiaryWriteEntry(diaryCreateViewModel, context)
    }
}





