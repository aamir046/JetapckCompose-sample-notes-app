package com.example.jetpackcompose.data.source.local.datasource

import com.example.jetpackcompose.data.source.local.entity.Note
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun getNoteById(id: Int): Note?
    suspend fun getNotes(): Flow<List<Note>>

}