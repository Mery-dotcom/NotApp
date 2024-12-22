package com.geeks.notapp.ui.interfaces

import com.geeks.notapp.data.models.NoteModel

interface OnClickItem {

    fun onLongClick(noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}