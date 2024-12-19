package com.geeks.notapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(
    val id: Int,
    val title: String,
    val description: String
){
    @PrimaryKey(autoGenerate = true)
    var Int = 0
}
