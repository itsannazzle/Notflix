package com.example.notflix.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.data.local.entity.TvShowEntity

class TvShowViewModel(private val moviesRepositories: MoviesRepositories) : ViewModel() {

    fun showTvShow() : LiveData<List<TvShowEntity>> = moviesRepositories.getAllPopularTvShow()

}