package com.example.notflix.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.ui.detail.DetailMoviesViewModel
import com.example.notflix.ui.favorite.FavoriteViewModel
import com.example.notflix.ui.movies.MoviesViewModel
import com.example.notflix.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val moviesRepositories: MoviesRepositories) : ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null
        fun getInstance(context: Context) : ViewModelFactory =
                instance ?: synchronized(this){
                    instance ?: ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(DetailMoviesViewModel::class.java) -> {
                return DetailMoviesViewModel(moviesRepositories) as T
            }
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                return MoviesViewModel(moviesRepositories) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(moviesRepositories) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                return FavoriteViewModel(moviesRepositories) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}