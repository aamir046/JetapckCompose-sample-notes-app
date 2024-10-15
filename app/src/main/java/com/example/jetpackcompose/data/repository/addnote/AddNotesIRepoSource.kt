package com.example.jetpackcompose.data.repository.addnote

import com.example.jetpackcompose.data.source.local.entity.Note
import kotlinx.coroutines.flow.Flow

interface AddNotesIRepoSource {
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun getNoteById(id: Int): Note?
}