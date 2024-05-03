package com.geeks.noteapp.interfaces

import com.geeks.noteapp.date.model.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)
    fun onClick(noteModel: NoteModel)
}