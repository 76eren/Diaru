package com.example.diaru.Settings

import android.content.Context
import android.preference.PreferenceManager
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.diaru.ui.theme.skyeBlue

class SettingsHandler {

    fun getSettingBoolean(key: String, default: Boolean, context: Context): Boolean {
        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedpreferences.getBoolean(key, default)
    }

    fun setSettingBoolean(key: String, value: Boolean, context: Context) {
        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedpreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    @Composable
    fun getColor(context: Context): Color {
        val settings = SettingsHandler()
        val color: Color
        if (settings.getSettingBoolean("preference_theme", false, context)) {
            color =  MaterialTheme.colorScheme.background
        }
        else {
            color = skyeBlue
        }
        return color
    }
}