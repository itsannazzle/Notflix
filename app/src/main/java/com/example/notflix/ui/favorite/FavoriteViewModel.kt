package com.example.notflix.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.data.MoviesRepositories

class FavoriteViewModel(private val moviesRepositories: MoviesRepositories) : ViewModel() {
    fun showFavMovie() : LiveData<PagedList<MoviesEntity>> = moviesRepositories.getAllFavMovie()

    fun showFavTv() : LiveData<PagedList<TvShowEntity>> = moviesRepositories.getAllFavTv()
}