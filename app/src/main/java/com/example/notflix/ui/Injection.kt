package com.example.notflix.ui

import android.content.Context
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context) : MoviesRepositories{
        val remoteDataSource = RemoteDataSource.getInstance()
        return MoviesRepositories.getInstance(remoteDataSource)
    }
}