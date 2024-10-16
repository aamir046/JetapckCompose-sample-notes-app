package com.example.jetpackcompose.data.repository.addnote

import com.example.jetpackcompose.data.source.local.entity.NoteEntity

interface AddNotesIRepoSource {
    suspend fun insertNote(noteEntity: NoteEntity)
    suspend fun updateNote(noteEntity: NoteEntity)
    suspend fun getNoteById(id: Int): NoteEntity?
}