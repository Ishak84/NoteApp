package com.geeks.noteapp

import com.geeks.noteapp.date.model.NoteModel

interface OnClick {
    fun onItemClick (noteModel: NoteModel)
}