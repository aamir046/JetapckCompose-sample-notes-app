package com.example.jetpackcompose.data.repository.noteshome

import com.example.jetpackcompose.data.source.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesHomeIRepoSource {
    suspend fun getNoteById(id: Int): NoteEntity?
    suspend fun getNotes(): Flow<List<NoteEntity>>
}