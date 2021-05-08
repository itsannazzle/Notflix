package com.example.notflix.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity

@Suppress("RedundantSuspendModifier")
class LocalDataRepository(private val dao: NotflixDao) {

    companion object{
        private val instance : LocalDataRepository? = null

        fun getInstance(notflixDao: NotflixDao) : LocalDataRepository = instance?: LocalDataRepository(notflixDao)
    }

    fun getAllMovies() : DataSource.Factory<Int,MoviesEntity> = dao.getAllMovies()

    fun getSelectedMovie(movieId: MoviesEntity) : LiveData<MoviesEntity> = dao.getSelectedMovie(movieId)

    suspend fun insertMovie(movie: List<MoviesEntity>) = dao.insertMovies(movie)

    suspend fun deleteMovie(movie: List<MoviesEntity>) = dao.deleteMovie(movie)

    fun getAllTvShow() : DataSource.Factory<Int,TvShowEntity> = dao.getAllTvShow()

    fun getSelectedTvShow(tvShowId: TvShowEntity) : LiveData<TvShowEntity> = dao.getSelectedTvShow(tvShowId)

    suspend fun insertTvShow(tvShow: List<TvShowEntity>) = dao.insertTvShow(tvShow)

    suspend fun deleteTvShow(tvShow: List<TvShowEntity>) = dao.insertTvShow(tvShow)
}