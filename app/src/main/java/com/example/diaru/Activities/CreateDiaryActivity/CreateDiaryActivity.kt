package com.example.diaru.Activities.CreateDiaryActivity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.diaru.Activities.CreateDiaryActivity.ui.theme.DiaruTheme
import com.example.diaru.database.diary.DiaryViewModel
import com.example.diaru.ui.theme.skyeBlue

class CreateDiaryActivity : ComponentActivity() {
    private val diaryViewModel: DiaryViewModel by viewModels()
    private val diaryCreateViewModel: DiaryCreateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiaruTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = skyeBlue) {
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
        UI_STATES.CONTENT_WRITE -> DiaryWriteEntry(diaryCreateViewModel)
    }
}





