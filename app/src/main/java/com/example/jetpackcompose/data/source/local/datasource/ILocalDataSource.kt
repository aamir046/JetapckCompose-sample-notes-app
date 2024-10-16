package com.example.jetpackcompose.data.source.local.datasource

import com.example.jetpackcompose.data.source.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    suspend fun insertNote(noteEntity: NoteEntity)
    suspend fun updateNote(noteEntity: NoteEntity)
    suspend fun getNoteById(id: Int): NoteEntity?
    suspend fun getNotes(): Flow<List<NoteEntity>>

}