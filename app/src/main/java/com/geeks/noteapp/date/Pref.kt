package com.geeks.noteapp.date

import android.content.Context
import android.content.SharedPreferences

class Pref(context: Context) {
    private val pref: SharedPreferences =
        context.getSharedPreferences("shared", Context.MODE_PRIVATE)

    var isOnBoardShown:Boolean
        get() = pref.getBoolean("board", false)
        set(value) = pref.edit().putBoolean("board", value).apply()
}

