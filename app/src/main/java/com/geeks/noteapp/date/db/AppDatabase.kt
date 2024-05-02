package com.geeks.noteapp.date.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.noteapp.date.daos.NoteDao
import com.geeks.noteapp.date.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
 abstract class AppDatabase: RoomDatabase() {
  abstract fun noteDao(): NoteDao
}