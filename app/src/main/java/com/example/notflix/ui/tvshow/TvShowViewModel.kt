package com.example.notflix.ui.tvshow

import androidx.lifecycle.ViewModel
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.TvShowEntity
import com.example.notflix.utils.DataMovies

class TvShowViewModel : ViewModel() {


    fun getTvShow() : List<TvShowEntity> = DataMovies.generateDataTvShow()


}