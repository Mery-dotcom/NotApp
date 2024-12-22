package com.geeks.notapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.notapp.data.db.daos.NoteDao
import com.geeks.notapp.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 3)
abstract class AppDataBase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}