package com.geeks.noteapp

import android.app.Application
import androidx.room.Room
import com.geeks.noteapp.date.Pref
import com.geeks.noteapp.date.db.AppDatabase

class App: Application() {
    companion object{
        var appDataBase: AppDatabase? = null
    }
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = Pref(this)
        getInstance()
    }

    fun getInstance(): AppDatabase? {
        if (appDataBase ==null){
            appDataBase = applicationContext?.let {
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java,
                    "note.databease"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDataBase
    }
}