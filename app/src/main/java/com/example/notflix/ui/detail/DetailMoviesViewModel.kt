package com.example.notflix.ui.detail

import androidx.lifecycle.*
import com.nextint.core.domain.model.MoviesModel
import com.nextint.core.domain.model.TvShowModel
import com.nextint.core.domain.usecase.NotflixUsecase
import com.nextint.core.values.ResourceData

class DetailMoviesViewModel(private val notflixUsecase: NotflixUsecase) : ViewModel() {

     var moviesModel = MoviesModel()
     var tvShowModel = TvShowModel()



    fun getDetailMovie(movieId: Int) = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getDetailMovie(movieId))

    fun getDetailTvShow(tvshowId: Int) = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getDetailTv(tvshowId))

    fun showEpisodes() = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getEpisodes())

    fun showTrendingMovies() = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getAllTrendingMovies())


    fun isFavoriteMovie(){
                val favState = !moviesModel.favorite
                    notflixUsecase.insertFavMovie(moviesModel,favState)

            }



    fun isFavoriteTv(){

        val favState = !tvShowModel.favorite
        notflixUsecase.insertFavTv(tvShowModel,favState)
    }


}