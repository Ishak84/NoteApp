package com.geeks.noteapp.date.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.geeks.noteapp.date.model.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: NoteModel)

    @Query("SELECT * FROM noteModel")
    fun getAll(): LiveData<List<NoteModel>>

    @Delete
    fun deleteNote(noteModel: NoteModel)

    @Update
    fun updateNote(noteModel: NoteModel)

    @Query("SELECT * FROM noteModel WHERE id = :id ")
    fun getNoteById(id:Int): NoteModel?
}