package com.example.jetpackcompose.data.source.local.datasource

import com.example.jetpackcompose.data.source.local.dao.NoteDao
import com.example.jetpackcompose.data.source.local.entity.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val noteDao: NoteDao
):ILocalDataSource {
    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun getNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

}