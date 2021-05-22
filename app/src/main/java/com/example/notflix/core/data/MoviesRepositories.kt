package com.example.notflix.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.notflix.core.data.local.LocalDataSource
import com.example.notflix.core.data.local.entity.EpisodesEntity
import com.example.notflix.core.data.local.entity.MoviesEntity
import com.example.notflix.core.data.local.entity.TvShowEntity
import com.example.notflix.core.data.remote.config.ApiResponse
import com.example.notflix.core.data.remote.RemoteDataSource
import com.example.notflix.core.data.remote.response.*
import com.example.notflix.core.domain.repository.NotflixImpl
import com.example.notflix.utils.AppExecutor
import com.example.notflix.utils.DataMovies
import com.example.notflix.values.ResourceData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoviesRepositories(private val remoteDataSource: RemoteDataSource,
                         private val localDataSource: LocalDataSource,
                         private val appExecutor: AppExecutor) : NotflixImpl {
    companion object{
        @Volatile
        private var instance : MoviesRepositories? = null
        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutor: AppExecutor) : MoviesRepositories =
                instance ?: synchronized(this){
                    instance ?: MoviesRepositories(remoteDataSource,localDataSource,appExecutor).apply { instance = this }
                }
    }

    override fun getAllTrendingMovies(): Flowable<ResourceData<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<List<ResultsItem>, PagedList<MoviesEntity>>() {
            override fun loadFromDB(): Flowable<PagedList<MoviesEntity>> {
                val config =PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(7)
                        .setPageSize(7)
                        .build()
                return RxPagedListBuilder(localDataSource.getAllMovies(),config).buildFlowable(BackpressureStrategy.BUFFER)

            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(response: List<ResultsItem>) {
                val movieList = ArrayList<MoviesEntity>()
                for (respon in response){
                    with(respon){
                        val movie = MoviesEntity(
                                id,
                                poster = posterPath,
                                title = title,
                                overview = overview,
                                backDrop = backdropPath,
                                rating = voteAverage!!
                        )
                        movieList.add(movie)
                    }
                }
                GlobalScope.launch(Dispatchers.IO){
                    localDataSource.insertMovie(movieList)
                }
            }

            override fun createCall(): Flowable<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getTrendingMovies()
            }

            override fun onFetchFailed() {
                Log.i("MoviesRepository","Fetch trending data movies failed")
            }
        }.asFlowable()
    }

    override fun getAllPopularTvShow(): Flowable<ResourceData<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TVResultsItem>, PagedList<TvShowEntity>>() {
            override fun loadFromDB(): Flowable<PagedList<TvShowEntity>> {
                val config =PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(7)
                        .setPageSize(7)
                        .build()
                return RxPagedListBuilder(localDataSource.getAllTvShow(),config).buildFlowable(BackpressureStrategy.BUFFER)
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(response: List<TVResultsItem>) {
                val tvshowList = ArrayList<TvShowEntity>()

                for (respon in response){
                    with(respon){
                        val tvshow = TvShowEntity(
                                id,
                                poster = posterPath,
                                title = name,
                                overview = overview,
                                backDrop = backdropPath,
                                rating = voteAverage!!
                        )
                        tvshowList.add(tvshow)
                    }
                }
                GlobalScope.launch(Dispatchers.IO){
                    localDataSource.insertTvShow(tvshowList)
                }
            }

            override fun createCall(): Flowable<ApiResponse<List<TVResultsItem>>> {
                return remoteDataSource.getPopularTvShow()
            }

            override fun onFetchFailed() {
                Log.i("MoviesRepository","Fetch popular tvshow data failed")
            }
        }.asFlowable()
    }

    override fun getDetailMovie(movie_id: Int): Flowable<ResourceData<MoviesEntity>> {
        return object : NetworkBoundResource<DetailMoviesResponse, MoviesEntity>() {
            override fun loadFromDB(): Flowable<MoviesEntity> {
                return localDataSource.getSelectedMovie(movie_id)
            }

            override fun shouldFetch(data: MoviesEntity?): Boolean {
                if (data != null) {
                    when{
                        data.genre.isNullOrEmpty() && data.country.isNullOrEmpty() -> return true
                    }
                }
                return false
            }

            override fun saveCallResult(response: DetailMoviesResponse) {
                val movieList = ArrayList<MoviesEntity>()
                val listGenre = ArrayList<String>()
                val listCountry = ArrayList<String>()
                    with(response){
                        for (genre in genres){
                                listGenre.add(genre.name)
                            }
                        for (country in productionCountries){
                            listCountry.add(country.name)
                        }
                        val movie = MoviesEntity(
                                id,
                                posterPath,
                                backdropPath,
                                title,
                                listGenre.toString(),
                                listCountry.toString(),
                                voteAverage,
                                overview,
                                runtime
                        )
                        movieList.add(movie)

                    }
                GlobalScope.launch(Dispatchers.IO){
                    localDataSource.updateMovie(movie_id,listGenre.toString(),listCountry.toString(),response.runtime)
                }

            }

            override fun createCall(): Flowable<ApiResponse<DetailMoviesResponse>> {
                return remoteDataSource.getDetailMovie(movie_id)
            }

            override fun onFetchFailed() {
                Log.i("MoviesRepository","Fetch detail movie data failed")
            }
        }.asFlowable()
    }

    override fun getDetailTv(tv_id: Int): Flowable<ResourceData<TvShowEntity>> {
        return object : NetworkBoundResource<DetailTvResponse, TvShowEntity>() {
            override fun loadFromDB(): Flowable<TvShowEntity> {
                return localDataSource.getSelectedTvShow(tv_id)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                if (data != null) {
                    when{
                        data.genre.isNullOrEmpty() && data.country.isNullOrEmpty() -> return true
                    }
                }
                return false
            }

            override fun saveCallResult(response: DetailTvResponse) {
                val listTvShow = ArrayList<TvShowEntity>()
                with(response){
                    val listGenre = ArrayList<String>()
                    val listCountry = ArrayList<String>()
                    for (genre in genres){
                        listGenre.add(genre.name)
                    }
                    for (country in productionCountries){
                        listCountry.add(country.name)
                    }
                    val tvShow = TvShowEntity(
                            id,
                            backdropPath,
                            posterPath,
                            name,
                            listGenre.toString(),
                            listCountry.toString(),
                            voteAverage,
                            overview,
                            episodeRunTime.lastIndex,
                            numberOfEpisodes
                    )
                    listTvShow.add(tvShow)
                    GlobalScope.launch {
                        localDataSource.updateTvShow(tv_id,listGenre.toString(),listCountry.toString(),response.episodeRunTime[0])
                    }
                }
            }

            override fun createCall(): Flowable<ApiResponse<DetailTvResponse>> {
                return remoteDataSource.getDetailTvShow(tv_id)
            }

            override fun onFetchFailed() {
                Log.i("MoviesRepository","Fetch detail tvshow data failed")
            }
        }.asFlowable()
    }

    fun insertFavMovie(movie: MoviesEntity, isFavorite : Boolean){
            localDataSource.favoriteMovie(movie, isFavorite)
    }

    fun insertFavTv(tv: TvShowEntity, isFavorite: Boolean){
            localDataSource.favoriteTv(tv,isFavorite)
    }

    override fun getAllFavMovie(): Flowable<PagedList<MoviesEntity>> {
        val config =PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(3)
                .setPageSize(3)
                .build()
        return RxPagedListBuilder(localDataSource.getAllFavMovie(),config).buildFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getAllFavTv(): Flowable<PagedList<TvShowEntity>> {
        val config =PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(3)
                .setPageSize(3)
                .build()
        return RxPagedListBuilder(localDataSource.getAllFavTv(),config).buildFlowable(BackpressureStrategy.BUFFER)

    }

    override fun getEpisodes(): Flowable<List<EpisodesEntity>> {
        val showEp = PublishSubject.create<List<EpisodesEntity>>()
        showEp.onNext(DataMovies.generateEpisodes())
        return showEp.toFlowable(BackpressureStrategy.BUFFER)
    }

}