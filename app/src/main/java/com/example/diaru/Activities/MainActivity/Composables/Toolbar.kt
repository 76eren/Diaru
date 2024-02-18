package com.example.diaru.Activities.MainActivity.Composables

import android.content.Context
import android.content.res.Configuration
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.diaru.Activities.MainActivity.addBook
import com.example.diaru.Settings.SettingsHandler
import com.example.diaru.ui.theme.darkSkyBlue
import com.example.diaru.ui.theme.topBarDarkMode
import com.example.diaru.ui.theme.topBarLightMode

@Composable
fun toolbarColorPicker(context: Context): Color {
    val settings = SettingsHandler()
    val color: Color
    if (settings.getSettingBoolean("preference_theme", false, context)) {
        val nightModeFlags: Int = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> {
                color = topBarDarkMode
            }

            Configuration.UI_MODE_NIGHT_NO -> {
                color = topBarLightMode
            }

            else -> {
                color = MaterialTheme.colorScheme.background
            }
        }
    } else {
        color = darkSkyBlue
    }

    return color
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(context: Context) {
    // I am not sure if handling the theme in a custom way is the best way to do it
    val color: Color = toolbarColorPicker(context)


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