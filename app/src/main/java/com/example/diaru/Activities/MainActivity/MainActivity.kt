package com.example.diaru.Activities.MainActivity

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diaru.Activities.CreateDiaryActivity.CreateDiaryActivity
import com.example.diaru.Navbar.BottomNavigationBar
import com.example.diaru.Navbar.Screen
import com.example.diaru.Settings.SettingsHandler
import com.example.diaru.database.diary.DiaryViewModel
import com.example.diaru.ui.theme.*

class MainActivity : ComponentActivity() {
    private val diaryViewModel: DiaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaruTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyApp(this)
                }

            }
        }
    }
}

@Composable
fun MyApp(context: Context) {
    val navController = rememberNavController()

    val settings = SettingsHandler()
    val color: Color
    if (settings.getSettingBoolean("preference_theme", false, context)) {
        color =  MaterialTheme.colorScheme.background
    }
    else {
        color = skyeBlue
    }

    Scaffold(
        topBar = { Toolbar(context) },
        bottomBar = { BottomNavigationBar(navController) },
        backgroundColor = color
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)) {
            composable(Screen.Home.route) { }
            composable(Screen.Settings.route) { PreferenceScreen(context) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(context: Context) {

    val settings = SettingsHandler()

    // I am not sure if handling the theme in a custom way is the best way to do it
    val color: Color
    if (settings.getSettingBoolean("preference_theme", false, context)) {
        val nightModeFlags: Int = context.getResources().getConfiguration().uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> { color = topBarDarkMode }
            Configuration.UI_MODE_NIGHT_NO -> { color = topBarLightMode }
            else -> { color = MaterialTheme.colorScheme.background }
        }
    }
    else {
        color = darkSkyBlue
    }

    TopAppBar(
        title = { Text("", color = Color.Blue) },
        actions = {
            IconButton(onClick = { addBook(context) }) {
                Icon(Icons.Filled.Add, contentDescription = "Add", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

fun addBook(contenxt: Context) {
    val intent = Intent(contenxt, CreateDiaryActivity::class.java)
    contenxt.startActivity(intent)
}
