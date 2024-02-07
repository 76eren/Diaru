package com.example.diaru.Activities.MainActivity

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat.startActivity
import com.example.diaru.Settings.SettingsHandler


@Composable
fun SwitchItem(settingName: String, settingsKey: String, default: Boolean ,context: Context, reloadActivity: Boolean = false) {
    val settings = SettingsHandler()

    val switchState = remember { mutableStateOf(settings.getSettingBoolean(settingsKey, default, context)) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = settingName,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = switchState.value,
            onCheckedChange = {
                switchState.value = it
                settings.setSettingBoolean(settingsKey, it, context)
                if (reloadActivity) {
                    val intent = Intent(context, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(context, intent, null)
                }
            },
        )
    }
}

@Composable
fun PreferenceScreen(context: Context) {
    MaterialTheme {
        Column {
            SwitchItem("Use cursive font", "preference_cursive", default = true, context)
            SwitchItem("Use default system theme", "preference_theme", default = false, context, reloadActivity = true)
        }
    }
}
