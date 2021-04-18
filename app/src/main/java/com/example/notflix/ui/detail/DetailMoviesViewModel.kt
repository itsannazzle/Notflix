package com.example.notflix.ui.detail

import androidx.lifecycle.ViewModel
import com.example.notflix.entity.EpisodesEntity
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.TvShowEntity
import com.example.notflix.utils.DataMovies

class DetailMoviesViewModel : ViewModel() {
    private lateinit var movieId : String
    private lateinit var tvshowId : String

    fun getSelectedMovie(movieId : String){
        this.movieId = movieId
    }

    fun getSelectedTvShow(tvshowId : String){
        this.tvshowId = tvshowId
    }

    fun showDetailMovie() : MoviesEntity{
        lateinit var moviesEntity: MoviesEntity
        val dataMovies = DataMovies.generateDataMovies()
        for (movies in dataMovies){
            if (movies.id_movies == movieId){
                moviesEntity = movies
            }
        }
        return moviesEntity
    }

    fun showDetailTvShow() : TvShowEntity{
        lateinit var tvShowEntity: TvShowEntity
        val dataTv = DataMovies.generateDataTvShow()
        for (show in dataTv){
            if (show.id_tvshow == tvshowId){
                tvShowEntity = show
            }
        }
        return tvShowEntity
    }

    fun showEpisodes() : List<EpisodesEntity> = DataMovies.generateEpisodes()


}