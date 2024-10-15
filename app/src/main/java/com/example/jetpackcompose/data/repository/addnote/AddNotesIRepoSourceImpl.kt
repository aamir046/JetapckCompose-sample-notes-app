package com.example.jetpackcompose.data.repository.addnote

import com.example.jetpackcompose.data.source.local.datasource.ILocalDataSource
import com.example.jetpackcompose.data.source.local.entity.Note
import javax.inject.Inject

class AddNotesIRepoSourceImpl @Inject constructor(
    private val dataSource: ILocalDataSource
): AddNotesIRepoSource {
    override suspend fun insertNote(note: Note) {
        dataSource.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        dataSource.updateNote(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
       return dataSource.getNoteById(id)
    }
}