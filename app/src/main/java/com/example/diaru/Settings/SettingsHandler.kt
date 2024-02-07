package com.example.diaru.Settings

import android.content.Context
import android.preference.PreferenceManager

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
}