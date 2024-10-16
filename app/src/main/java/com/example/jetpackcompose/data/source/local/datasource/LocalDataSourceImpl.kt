package com.example.jetpackcompose.data.source.local.datasource

import com.example.jetpackcompose.data.source.local.dao.NoteDao
import com.example.jetpackcompose.data.source.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val noteDao: NoteDao
):ILocalDataSource {
    override suspend fun insertNote(noteEntity: NoteEntity) {
        noteDao.insertNote(noteEntity)
    }

    override suspend fun updateNote(noteEntity: NoteEntity) {
        noteDao.updateNote(noteEntity)
    }

    override suspend fun getNoteById(id: Int): NoteEntity? {
        return noteDao.getNoteById(id)
    }

    override suspend fun getNotes(): Flow<List<NoteEntity>> {
        return noteDao.getAllNotes()
    }

}