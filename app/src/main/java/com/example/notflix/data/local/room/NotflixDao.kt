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
    fun getSelectedMovie(movieId : Int) : LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MoviesEntity>)

    @Query("update moviesTable set genre =:genre, country =:country, duration =:duration where id_movies =:movieId")
    fun updateMovie(movieId: Int, genre : String,country : String, duration : Int)

    @Delete
    fun deleteMovie(movie: List<MoviesEntity>)

    @Query("select * from tvShowTable")
    fun getAllTvShow() : DataSource.Factory<Int,TvShowEntity>

    @Query("select * from tvShowTable where id_tvshow = :tvshowId")
    fun getSelectedTvShow(tvshowId: Int) : LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvshow: List<TvShowEntity>)

    @Delete
    fun deleteTvShow(tvshow: List<TvShowEntity>)

    @Insert
    fun insertFavotiteMovie(movieId: MoviesEntity)

    @Insert
    fun insertFavoriteTv(tvshowId: TvShowEntity)

    @Query("select * from moviesTable where id_movies= :movieId")
    fun checkFavorite(movieId: Int) : Boolean

    @Delete
    fun deleteFavoriteMovie(movieId: MoviesEntity)

    @Delete
    fun deleteFavoriteTv(tvshowId: TvShowEntity)

}