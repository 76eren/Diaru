package com.example.diaru.Activities.MainActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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

class MainActivity : ComponentActivity() {
    private val diaryViewModel: DiaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaruTheme {
                MyApp(this)
            }
        }
    }
}

@Composable
fun MyApp(context: Context) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { Toolbar(context) },
        bottomBar = { BottomNavigationBar(navController) }
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
        title = { Text("Actions", color = Color.White) },
        actions = {
            IconButton(onClick = { addBook(context) }) {
                Icon(Icons.Filled.Add, contentDescription = "Add", tint = Color.White)
            }
        },
        colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

fun addBook(contenxt: Context) {
    val intent = Intent(contenxt, CreateDiaryActivity::class.java)
    contenxt.startActivity(intent)
}
