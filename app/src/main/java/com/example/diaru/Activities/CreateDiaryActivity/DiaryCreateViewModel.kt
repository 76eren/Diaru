package com.example.diaru.Activities.CreateDiaryActivity

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DiaryCreateViewModel : ViewModel() {
    val feeling = mutableIntStateOf(-1)
    var contentScreen = mutableStateOf(UI_STATES.FEELING_SELECTION) // Default to showing the feeling selection screen
}

enum class UI_STATES {
    FEELING_SELECTION,
    CONTENT_INPUT
}