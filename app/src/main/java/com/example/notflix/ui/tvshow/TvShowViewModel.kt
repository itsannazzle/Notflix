package com.example.notflix.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.values.ResourceData

class TvShowViewModel(private val moviesRepositories: MoviesRepositories) : ViewModel() {

    fun showTvShow() : LiveData<ResourceData<PagedList<TvShowEntity>>> = moviesRepositories.getAllPopularTvShow()

}