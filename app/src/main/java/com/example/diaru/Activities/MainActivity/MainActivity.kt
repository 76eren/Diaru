package com.example.diaru.Activities.MainActivity

import android.content.Context
import android.content.Intent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diaru.Activities.CreateDiaryActivity.CreateDiaryActivity
import com.example.diaru.Navbar.BottomNavigationBar
import com.example.diaru.Navbar.Screen
import com.example.diaru.database.diary.DiaryViewModel
import com.example.diaru.ui.theme.DiaruTheme
import com.example.diaru.ui.theme.darkSkyBlue
import com.example.diaru.ui.theme.skyeBlue

class MainActivity : ComponentActivity() {
    private val diaryViewModel: DiaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaruTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = skyeBlue) {
                    MyApp(this)
                }

            }
        }
    }
}

@Composable
fun MyApp(context: Context) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { Toolbar(context) },
        bottomBar = { BottomNavigationBar(navController) },
        backgroundColor = skyeBlue
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)) {
            composable(Screen.Home.route) { }
            composable(Screen.Settings.route) { }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(context: Context) {
    TopAppBar(
        title = { Text("", color = Color.Blue) },
        actions = {
            IconButton(onClick = { addBook(context) }) {
                Icon(Icons.Filled.Add, contentDescription = "Add", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = darkSkyBlue,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

fun addBook(contenxt: Context) {
    val intent = Intent(contenxt, CreateDiaryActivity::class.java)
    contenxt.startActivity(intent)
}
