package com.example.diaru.Activities.MainActivity

import android.content.Context
import android.preference.PreferenceManager
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


@Composable
fun SwitchItem(settingName: String, settingsKey: String, default: Boolean ,context: Context) {
    val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = sharedpreferences.edit()
    val switchState = remember { mutableStateOf(sharedpreferences.getBoolean(settingsKey, default)) }

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
                editor.putBoolean(settingsKey, it)
                editor.apply()
            },
        )
    }
}

@Composable
fun PreferenceScreen(context: Context) {
    MaterialTheme {
        SwitchItem("Use cursive font", "preference_cursive", default = true, context)
    }
}
