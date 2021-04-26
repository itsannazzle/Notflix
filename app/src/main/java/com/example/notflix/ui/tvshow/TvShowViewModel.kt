package com.example.notflix.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.PrevTVEntity
import com.example.notflix.entity.TvShowEntity
import com.example.notflix.utils.DataMovies

class TvShowViewModel(private val moviesRepositories: MoviesRepositories) : ViewModel() {

    //fun getTvShow() : List<TvShowEntity> = DataMovies.generateDataTvShow()

    fun showTvShow() : LiveData<List<PrevTVEntity>> = moviesRepositories.getAllPopularTvShow()

}