package com.example.diaru.Activities.MainActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diaru.Activities.CreateDiaryActivity.CreateDiaryActivity
import com.example.diaru.Activities.MainActivity.Composables.ShowDiaryEntries
import com.example.diaru.Activities.MainActivity.Composables.Toolbar
import com.example.diaru.Navbar.BottomNavigationBar
import com.example.diaru.Navbar.Screen
import com.example.diaru.Settings.SettingsHandler
import com.example.diaru.database.diary.DiaryViewModel
import com.example.diaru.ui.theme.DiaruTheme


class MainActivity : ComponentActivity() {
    private val diaryViewModel: DiaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaruTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyApp(this, diaryViewModel)
                }

            }
        }
    }
}

@Composable
fun MyApp(context: Context, diaryViewModel: DiaryViewModel) {
    val navController = rememberNavController()

    val color = SettingsHandler().getColor(context)

    Scaffold(
        topBar = { Toolbar(context) },
        bottomBar = { BottomNavigationBar(navController) },
        backgroundColor = color
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)) {
            composable(Screen.Home.route) { ShowDiaryEntries(diaryViewModel) }
            composable(Screen.Settings.route) { PreferenceScreen(context) }
        }
    }

}


fun addBook(contenxt: Context) {
    val intent = Intent(contenxt, CreateDiaryActivity::class.java)
    contenxt.startActivity(intent)
}

