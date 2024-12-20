package com.geeks.notapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(
    val id: Int = 0,
    val title: String,
    val description: String,
    val date: String,
    val time: String
){
    @PrimaryKey(autoGenerate = true)
    var Int = 0
}
