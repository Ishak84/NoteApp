package com.geeks.noteapp.interfaces

import com.geeks.noteapp.date.model.NoteModel

interface OnClick {
    fun onItemClick (noteModel: NoteModel)
}