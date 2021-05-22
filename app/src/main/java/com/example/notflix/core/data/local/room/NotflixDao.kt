package com.example.notflix.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.notflix.core.data.local.entity.MoviesEntity
import com.example.notflix.core.data.local.entity.TvShowEntity
import io.reactivex.Flowable

@Dao
interface NotflixDao {

    @Query("select * from moviesTable")
    fun getAllMovies() : DataSource.Factory<Int,MoviesEntity>

    @Query("select * from moviesTable where id_movies = :movieId")
    fun getSelectedMovie(movieId : Int) : Flowable<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvshow: List<TvShowEntity>)

    @Query("update moviesTable set genre =:genre, country =:country, duration =:duration where id_movies =:movieId")
    fun updateMovie(movieId: Int, genre : String,country : String, duration : Int)

    @Query("update tvShowTable set genre =:genre, country =:country, duration =:duration where id_tvshow =:tvId")
    fun updateTvShow(tvId: Int, genre : String,country : String, duration : Int)

    @Query("select * from tvShowTable")
    fun getAllTvShow() : DataSource.Factory<Int,TvShowEntity>

    @Query("select * from tvShowTable where id_tvshow = :tvshowId")
    fun getSelectedTvShow(tvshowId: Int) : Flowable<TvShowEntity>

    @Update
    suspend fun insertFavotiteMovie(movie: MoviesEntity)

    @Update
    suspend fun insertFavoriteTv(tvshow: TvShowEntity)

    @Query("select count(id_movies) and count(favorite) from moviesTable where id_movies= :idMovie")
    fun checkFavorite(idMovie: Int) : Int

    @Query("select * from moviesTable where favorite= :favotiteMovie")
    fun getAllFavoriteMovie(favotiteMovie : Boolean = true) : DataSource.Factory<Int,MoviesEntity>

    @Query("select * from tvshowtable where favorite= :favotiteTv")
    fun getAllFavoriteTv(favotiteTv : Boolean = true) : DataSource.Factory<Int,TvShowEntity>

    @Delete
    suspend fun deleteFavoriteMovie(movieId: MoviesEntity)

    @Delete
    suspend fun deleteFavoriteTv(tvshowId: TvShowEntity)

}