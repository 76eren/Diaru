package com.example.diaru.Activities.CreateDiaryActivity

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class DiaryCreateViewModel : ViewModel() {
    val feeling = mutableIntStateOf(-1)
}