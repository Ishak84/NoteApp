package com.geeks.noteapp.date

import android.content.Context
import android.content.SharedPreferences

class Pref(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("shered", Context.MODE_PRIVATE)


    var title: String?
        get() = sharedPreferences.getString("title", "")
        set(value) = sharedPreferences.edit().putString("title", value).apply()

    var isOnBoardShown: Boolean
        get() = sharedPreferences.getBoolean("board",false)
        set(value) = sharedPreferences.edit().putBoolean("board", value).apply()
}
