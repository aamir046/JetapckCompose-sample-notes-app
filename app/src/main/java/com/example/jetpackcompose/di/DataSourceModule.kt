package com.example.jetpackcompose.di

import com.example.jetpackcompose.data.source.local.datasource.ILocalDataSource
import com.example.jetpackcompose.data.source.local.datasource.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(dataSource: LocalDataSourceImpl): ILocalDataSource

}