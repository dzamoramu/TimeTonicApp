package com.example.timetonicapp

import android.content.Context

class SharedPreferencesManager(
    context: Context
) {
    private val sharedPreferences =  context.getSharedPreferences("SESSION_KEY", Context.MODE_PRIVATE)

    fun saveKey(key: String) {
        val editor = sharedPreferences.edit()
        editor.putString("session_key", key)
        editor.apply()
    }

    fun getKey(): String? {
        return sharedPreferences.getString("session_key", null)
    }
}