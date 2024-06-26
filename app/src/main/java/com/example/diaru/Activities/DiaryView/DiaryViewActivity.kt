package com.example.diaru.Activities.DiaryView

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.diaru.Activities.SharedComposables.DiaryReadWrite.DiaryReadWriteEntry
import com.example.diaru.Activities.CreateDiaryActivity.DiaryCreateViewModel
import com.example.diaru.Activities.MainActivity.Composables.toolbarColorPicker
import com.example.diaru.Activities.MainActivity.MainActivity
import com.example.diaru.Settings.SettingsHandler
import com.example.diaru.database.diary.DiaryViewModel
import com.example.diaru.ui.theme.DiaruTheme

class DiaryView : ComponentActivity() {
    private val diaryViewModel: DiaryViewModel by viewModels()
    private val diaryCreateViewModel: DiaryCreateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaruTheme {
                val color = SettingsHandler().getColor(this)
                Surface(modifier = Modifier.fillMaxSize(), color = color) {
                    DiaryViewApp(diaryViewModel, diaryCreateViewModel)
                }
            }
        }
    }
}

@Composable
fun DiaryViewContent(
    diaryViewModel: DiaryViewModel,
    diaryCreateViewModel: DiaryCreateViewModel,
    edit: Boolean = false
) {
    val intent = (LocalContext.current as Activity).intent
    val diaryId = intent.getStringExtra("id")

    val diaryEntries by diaryViewModel.allEntries.observeAsState(initial = emptyList())
    for (i in diaryEntries) {
        if (i.id == diaryId) {
            diaryCreateViewModel.title.value = i.title
            diaryCreateViewModel.content.value = i.content
            diaryCreateViewModel.feeling.intValue = i.feeling
            DiaryReadWriteEntry(
                diaryCreateViewModel,
                LocalContext.current,
                diaryEntity = i,
                edit = edit,
                diaryViewModel = diaryViewModel
            )
            break
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryViewApp(diaryViewModel: DiaryViewModel, diaryViewCreateViewModel: DiaryCreateViewModel) {
    val context = LocalContext.current
    val color: Color = toolbarColorPicker(context)
    var edit by remember { mutableStateOf(false) }
    var delete by remember { mutableStateOf(false) }

    Column {
        TopAppBar(title = { Text("") }, navigationIcon = {
        },
            actions = {
                IconButton(onClick = { edit = true }) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit", tint = Color.White)
                }
                IconButton(onClick = { delete = true }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = color,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White
            )
        )

        if (delete) {
            DeleteDiaryEntry(diaryViewModel)
            return
        }
        if (edit) {
            DiaryViewContent(diaryViewModel, diaryViewCreateViewModel, edit = true)
        } else {
            DiaryViewContent(diaryViewModel, diaryViewCreateViewModel, edit = false)
        }
    }
}

@Composable
fun DeleteDiaryEntry(diaryViewModel: DiaryViewModel) {
    val intent = (LocalContext.current as Activity).intent
    val diaryId = intent.getStringExtra("id")

    val diaryEntries by diaryViewModel.allEntries.observeAsState(initial = emptyList())
    for (i in diaryEntries) {
        if (i.id == diaryId) {
            diaryViewModel.delete(i)
            val mainIntent = Intent(LocalContext.current, MainActivity::class.java)
            LocalContext.current.startActivity(mainIntent)
            break
        }
    }
}