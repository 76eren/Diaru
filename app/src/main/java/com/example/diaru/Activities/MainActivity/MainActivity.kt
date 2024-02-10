package com.example.diaru.Activities.MainActivity

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diaru.Activities.CreateDiaryActivity.CreateDiaryActivity
import com.example.diaru.Navbar.BottomNavigationBar
import com.example.diaru.Navbar.Screen
import com.example.diaru.R
import com.example.diaru.Settings.SettingsHandler
import com.example.diaru.database.diary.DiaryEntity
import com.example.diaru.database.diary.DiaryViewModel
import com.example.diaru.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*


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
            composable(Screen.Home.route) { ShowDiaryEntries(diaryViewModel) }
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

@Composable
fun ShowDiaryEntries(diaryViewModel: DiaryViewModel) {
    val diaryEntries by diaryViewModel.allEntries.observeAsState(initial = emptyList())

    LazyColumn {
        items(diaryEntries) { entry ->
            DiaryEntryItem(entry)
        }
    }
}

@Composable
fun DiaryEntryItem(entry: DiaryEntity) {
    val dateInt = entry.date
    val date: Date = Date()
    val formatter = SimpleDateFormat("MM-dd-yyyy : HH:mm")
    val formattedDate: String = formatter.format(date)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        // TODO: Fix the background colour

        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    )
    {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = painterResource(id = getFeelingImageResource(entry.feeling)),
                contentDescription = "Feeling image",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = entry.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }


}


fun getFeelingImageResource(feelingNumber: Int): Int {
    return when (feelingNumber) {
        1 -> R.drawable.crying_lots
        2 -> R.drawable.crying
        3 -> R.drawable.neutral
        4 -> R.drawable.happy
        5 -> R.drawable.very_happy
        else -> R.drawable.ic_launcher_background
    }
}

