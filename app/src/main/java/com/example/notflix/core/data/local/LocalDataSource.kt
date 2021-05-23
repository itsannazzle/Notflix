package com.example.notflix.core.data.local

import androidx.paging.DataSource
import com.example.notflix.core.data.local.entity.MoviesEntity
import com.example.notflix.core.data.local.entity.TvShowEntity
import com.example.notflix.core.data.local.room.NotflixDao
import io.reactivex.Flowable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDataSource(private val dao: NotflixDao) {
  /*  companion object{
        private val instance : LocalDataSource? = null
        fun getInstance(notflixDao: NotflixDao) : LocalDataSource = instance ?: LocalDataSource(notflixDao)
    }*/

    fun getAllMovies() : DataSource.Factory<Int,MoviesEntity> = dao.getAllMovies()

    fun getAllTvShow() : DataSource.Factory<Int,TvShowEntity> = dao.getAllTvShow()

    fun getSelectedMovie(movieId : Int) : Flowable<MoviesEntity> = dao.getSelectedMovie(movieId)

    fun getSelectedTvShow(tvshowId : Int) : Flowable<TvShowEntity> = dao.getSelectedTvShow(tvshowId)

    fun insertMovie(movie: List<MoviesEntity>) = dao.insertMovies(movie)

    fun updateMovie(movieId: Int,genre : String,country : String, duration : Int) = dao.updateMovie(movieId, genre, country, duration)

    fun updateTvShow(tvshowId: Int,genre : String,country : String, duration : Int) = dao.updateTvShow(tvshowId, genre, country, duration)


    fun insertTvShow(tvshow: List<TvShowEntity>) = dao.insertTvShow(tvshow)



    fun favoriteMovie(movie: MoviesEntity,isFavorite : Boolean) {
        movie.favorite = isFavorite
        GlobalScope.launch {
            dao.insertFavotiteMovie(movie)
        }

    }

    fun favoriteTv(tvshow: TvShowEntity, isFavorite: Boolean) {
        tvshow.favorite = isFavorite
       GlobalScope.launch {
           dao.insertFavoriteTv(tvshow)
       }
    }

    fun getAllFavMovie() : DataSource.Factory<Int, MoviesEntity> = dao.getAllFavoriteMovie()

    fun getAllFavTv() : DataSource.Factory<Int, TvShowEntity> = dao.getAllFavoriteTv()




}