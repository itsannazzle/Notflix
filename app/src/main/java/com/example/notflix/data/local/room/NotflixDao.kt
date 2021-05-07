package com.example.notflix.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity

@Dao
interface NotflixDao {

    @Query("select * from moviesTable")
    fun getAllMovies() : DataSource.Factory<Int,MoviesEntity>

    @Query("select * from moviesTable where id_movies = :movieId")
    fun getSelectedMovie(movieId : MoviesEntity) : LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieId: List<MoviesEntity>)

    @Delete
    fun deleteMovie(movieId: List<MoviesEntity>)

    @Query("select * from tvShowTable")
    fun getAllTvShow() : DataSource.Factory<Int,TvShowEntity>

    @Query("select * from tvShowTable where id_tvshow = :tvshowId")
    fun getSelectedTvShow(tvshowId: TvShowEntity) : LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvshowId: List<TvShowEntity>)

    @Delete
    fun deleteTvShow(tvshowId: List<TvShowEntity>)
}