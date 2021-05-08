package com.example.notflix.ui

import android.content.Context
import com.example.notflix.data.local.LocalDataSource
import com.example.notflix.data.local.room.NotflixDatabase
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context) : MoviesRepositories{
        val remoteDataSource = RemoteDataSource.getInstance()
        val database = NotflixDatabase.getDBInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.notflixDao())
        return MoviesRepositories.getInstance(remoteDataSource,localDataSource)
    }
}