package com.example.jetpackcompose.data.repository.addnote

import com.example.jetpackcompose.data.source.local.datasource.ILocalDataSource
import com.example.jetpackcompose.data.source.local.entity.NoteEntity
import javax.inject.Inject

class AddNotesIRepoSourceImpl @Inject constructor(
    private val dataSource: ILocalDataSource
): AddNotesIRepoSource {
    override suspend fun insertNote(noteEntity: NoteEntity) {
        dataSource.insertNote(noteEntity)
    }

    override suspend fun updateNote(noteEntity: NoteEntity) {
        dataSource.updateNote(noteEntity)
    }

    override suspend fun getNoteById(id: Int): NoteEntity? {
       return dataSource.getNoteById(id)
    }
}