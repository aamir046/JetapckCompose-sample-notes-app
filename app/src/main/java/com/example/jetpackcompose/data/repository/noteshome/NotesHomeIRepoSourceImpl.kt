package com.example.jetpackcompose.data.repository.noteshome

import com.example.jetpackcompose.data.source.local.datasource.ILocalDataSource
import com.example.jetpackcompose.data.source.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesHomeIRepoSourceImpl @Inject constructor(
    private val dataSource: ILocalDataSource
): NotesHomeIRepoSource {

    override suspend fun getNoteById(id: Int): NoteEntity? {
       return dataSource.getNoteById(id)
    }

    override suspend fun getNotes(): Flow<List<NoteEntity>> {
        return dataSource.getNotes()
    }
}