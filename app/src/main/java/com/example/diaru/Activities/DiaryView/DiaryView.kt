package com.example.diaru.Activities.DiaryView

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diaru.Activities.MainActivity.*
import com.example.diaru.Navbar.BottomNavigationBar
import com.example.diaru.Navbar.Screen
import com.example.diaru.Settings.SettingsHandler
import com.example.diaru.ui.theme.*

class DiaryView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaruTheme {
                val color = SettingsHandler().getColor(this)
                Surface(modifier = Modifier.fillMaxSize(), color = color) {
                    DiaryViewApp()
                }
            }
        }
    }
}

@Composable
fun DiaryViewContent() {
    Text("Hello world")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryViewApp() {
    val context = LocalContext.current

    val color: Color = toolbarColorPicker(context)


    Column {
        TopAppBar(title = { Text("") }, navigationIcon = {
        },
            actions = {
                IconButton(onClick = {  }) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = color,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White
            )

        )
        DiaryViewContent()
    }

}
