package com.geeks.noteapp

import android.app.Application
import com.geeks.noteapp.date.Pref

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = Pref(this)
    }
}