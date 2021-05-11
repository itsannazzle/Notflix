package com.example.notflix.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.data.local.room.NotflixDao

class LocalDataSource private constructor(private val dao: NotflixDao) {
    companion object{
        private val instance : LocalDataSource? = null
        fun getInstance(notflixDao: NotflixDao) : LocalDataSource = instance ?: LocalDataSource(notflixDao)
    }

    fun getAllMovies() : DataSource.Factory<Int,MoviesEntity> = dao.getAllMovies()

    fun getAllTvShow() : DataSource.Factory<Int,TvShowEntity> = dao.getAllTvShow()

    fun getSelectedMovie(movieId : Int) : LiveData<MoviesEntity> = dao.getSelectedMovie(movieId)

    fun getSelectedTvShow(tvshowId : Int) : LiveData<TvShowEntity> = dao.getSelectedTvShow(tvshowId)

    fun insertMovie(movie: List<MoviesEntity>) = dao.insertMovies(movie)

    fun updateMovie(movieId: Int,genre : String,country : String, duration : Int) = dao.updateMovie(movieId,genre, country, duration)

    fun insertTvShow(tvshow: List<TvShowEntity>) = dao.insertTvShow(tvshow)

    fun deleteMovie(movie: List<MoviesEntity>) = dao.deleteMovie(movie)

    fun deleteTvShow(tvshow: List<TvShowEntity>) = dao.deleteTvShow(tvshow)

    fun favoriteMovie(movieId: MoviesEntity) = dao.insertFavotiteMovie(movieId)

    fun favoriteTv(tvshowId: TvShowEntity) = dao.insertFavoriteTv(tvshowId)

    fun deleteFavMovie(movieId: MoviesEntity) = dao.deleteFavoriteMovie(movieId)

    fun deleteFavTv(tvshowId: TvShowEntity) = dao.deleteFavoriteTv(tvshowId)

    fun checkFavorite(movieId: Int) : Boolean = dao.checkFavorite(movieId)



}