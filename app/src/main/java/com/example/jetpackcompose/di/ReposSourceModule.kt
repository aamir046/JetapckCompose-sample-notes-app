package com.example.jetpackcompose.di

import com.example.jetpackcompose.data.repository.addnote.AddNotesIRepoSource
import com.example.jetpackcompose.data.repository.addnote.AddNotesIRepoSourceImpl
import com.example.jetpackcompose.data.repository.noteshome.NotesHomeIRepoSource
import com.example.jetpackcompose.data.repository.noteshome.NotesHomeIRepoSourceImpl
import com.example.jetpackcompose.data.source.local.datasource.ILocalDataSource
import com.example.jetpackcompose.data.source.local.datasource.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ReposSourceModule {

    @Singleton
    @Binds
    abstract fun bindAddNotesRepository(addNotesIRepoSourceImpl: AddNotesIRepoSourceImpl): AddNotesIRepoSource

    @Singleton
    @Binds
    abstract fun bindNotesHomeRepository(notesHomeIRepoSourceImpl: NotesHomeIRepoSourceImpl): NotesHomeIRepoSource

}