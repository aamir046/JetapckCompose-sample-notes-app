package com.example.jetpackcompose.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetpackcompose.data.source.local.DateConverter
import com.example.jetpackcompose.data.source.local.dao.NoteDao
import com.example.jetpackcompose.data.source.local.entity.Note

@Database(entities = [Note::class], version = 1)
@TypeConverters(DateConverter::class) // If you're using custom types like Date
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
