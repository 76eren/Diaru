package com.example.diaru.Activities.CreateDiaryActivity

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DiaryCreateViewModel : ViewModel() {
    var feeling = mutableIntStateOf(-1)
    var title = mutableStateOf("")
    var content = mutableStateOf("")
    var contentScreen = mutableStateOf(UI_STATES.FEELING_SELECTION) // Default to showing the feeling selection screen
    var customDate = mutableStateOf(System.currentTimeMillis())
}

enum class UI_STATES {
    FEELING_SELECTION,
    TITLE_INPUT,
    CONTENT_WRITE,
    CONTENT_ADD
}