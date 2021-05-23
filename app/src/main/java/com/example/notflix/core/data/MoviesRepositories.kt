package com.example.notflix.core.data

import android.util.Log
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.notflix.core.data.local.LocalDataSource
import com.example.notflix.core.data.local.entity.EpisodesEntity
import com.example.notflix.core.data.local.entity.MoviesEntity
import com.example.notflix.core.data.local.entity.TvShowEntity
import com.example.notflix.core.data.remote.RemoteDataSource
import com.example.notflix.core.data.remote.config.ApiResponse
import com.example.notflix.core.data.remote.response.DetailMoviesResponse
import com.example.notflix.core.data.remote.response.DetailTvResponse
import com.example.notflix.core.data.remote.response.ResultsItem
import com.example.notflix.core.data.remote.response.TVResultsItem
import com.example.notflix.core.domain.model.MoviesModel
import com.example.notflix.core.domain.model.TvShowModel
import com.example.notflix.core.domain.repository.NotflixImpl
import com.example.notflix.utils.AppExecutor
import com.example.notflix.utils.DataMapper
import com.example.notflix.utils.DataMovies
import com.example.notflix.values.ResourceData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

    override fun getAllTrendingMovies(): Flowable<ResourceData<PagedList<MoviesModel>>> {
        return object : NetworkBoundResource<List<ResultsItem>, PagedList<MoviesModel>>() {
            override fun loadFromDB(): Flowable<PagedList<MoviesModel>> {
                val config =PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(7)
                        .setPageSize(7)
                        .build()
                return RxPagedListBuilder(localDataSource.getAllFavMovie()
                    .map { DataMapper.mapMoviesEntitiesToDomain(it)
                         },config)
                    .buildFlowable(BackpressureStrategy.BUFFER)


            }

            override fun shouldFetch(data: PagedList<MoviesModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(response: List<ResultsItem>) {
                val movieList = DataMapper.mapMoviesResponseToEntity(response)
                    localDataSource.insertMovie(movieList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getTrendingMovies()
            }

            override fun onFetchFailed() {
                Log.i("MoviesRepository","Fetch trending data movies failed")
            }
        }.asFlowable()
    }

    override fun getAllPopularTvShow(): Flowable<ResourceData<PagedList<TvShowModel>>> {
        return object : NetworkBoundResource<List<TVResultsItem>, PagedList<TvShowModel>>() {
            override fun loadFromDB(): Flowable<PagedList<TvShowModel>> {
                val config =PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(7)
                        .setPageSize(7)
                        .build()
                return RxPagedListBuilder(localDataSource.getAllTvShow()
                    .map { DataMapper.mapTvShowEntitiesToDomain(it)
                },config).buildFlowable(BackpressureStrategy.BUFFER)
            }

            override fun shouldFetch(data: PagedList<TvShowModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(response: List<TVResultsItem>) {
                val tvshowList = DataMapper.mapTvShowResponseToEntity(response)
                localDataSource.insertTvShow(tvshowList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<List<TVResultsItem>>> {
                return remoteDataSource.getPopularTvShow()
            }

            override fun onFetchFailed() {
                Log.i("MoviesRepository","Fetch popular tvshow data failed")
            }
        }.asFlowable()
    }

    override fun getDetailMovie(movie_id: Int): Flowable<ResourceData<MoviesModel>> {
        return object : NetworkBoundResource<DetailMoviesResponse, MoviesModel>() {
            override fun loadFromDB(): Flowable<MoviesModel> {
                return localDataSource.getSelectedMovie(movie_id).map { DataMapper.mapMoviesEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: MoviesModel?): Boolean {
                if (data != null) {
                    when{
                        data.genre.isNullOrEmpty() && data.country.isNullOrEmpty() -> return true
                    }
                }
                return false
            }

            override fun saveCallResult(response: DetailMoviesResponse) {
                val movieList = DataMapper.mapMoviesDetailResponseToEntity(response)
                    with(movieList){
                        localDataSource.updateMovie(id_movies,genre.toString(),country.toString(),duration)
                    }  .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<DetailMoviesResponse>> {
                return remoteDataSource.getDetailMovie(movie_id)
            }

            override fun onFetchFailed() {
                Log.i("MoviesRepository","Fetch detail movie data failed")
            }
        }.asFlowable()
    }

    override fun getDetailTv(tv_id: Int): Flowable<ResourceData<TvShowModel>> {
        return object : NetworkBoundResource<DetailTvResponse, TvShowModel>() {
            override fun loadFromDB(): Flowable<TvShowModel> {
                return localDataSource.getSelectedTvShow(tv_id).map { DataMapper.mapTvShowEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: TvShowModel?): Boolean {
                if (data != null) {
                    when{
                        data.genre.isNullOrEmpty() && data.country.isNullOrEmpty() -> return true
                    }
                }
                return false
            }

            override fun saveCallResult(response: DetailTvResponse) {
                val listTvShow = DataMapper.mapTvShowDetailResponseToEntity(response)
                with(listTvShow){
                    localDataSource.updateTvShow(id_tvshow,genre.toString(),country.toString(),duration)
                }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<DetailTvResponse>> {
                return remoteDataSource.getDetailTvShow(tv_id)
            }

            override fun onFetchFailed() {
                Log.i("MoviesRepository","Fetch detail tvshow data failed")
            }
        }.asFlowable()
    }

    fun insertFavMovie(movie: MoviesModel, isFavorite : Boolean){
        val favMovie = DataMapper.mapDomainMovieToEntity(movie)
            appExecutor.diskIO().execute {
                localDataSource.favoriteMovie(favMovie, isFavorite)
            }
    }

    fun insertFavTv(tv: TvShowModel, isFavorite: Boolean){
        val favTv = DataMapper.mapDomainTvToEntity(tv)
        appExecutor.diskIO().execute {
            localDataSource.favoriteTv(favTv,isFavorite)
        }
    }

    override fun getAllFavMovie(): Flowable<PagedList<MoviesModel>> {
        val config =PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(3)
                .setPageSize(3)
                .build()
        return RxPagedListBuilder(localDataSource.getAllFavMovie()
            .map { DataMapper.mapMoviesEntitiesToDomain(it) },config).buildFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getAllFavTv(): Flowable<PagedList<TvShowModel>> {
        val config =PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(3)
                .setPageSize(3)
                .build()
        return RxPagedListBuilder(localDataSource.getAllFavTv()
            .map {
                 DataMapper.mapTvShowEntitiesToDomain(it)
            },config).buildFlowable(BackpressureStrategy.BUFFER)

    }

    override fun getEpisodes(): Flowable<List<EpisodesEntity>> {
        val showEp = PublishSubject.create<List<EpisodesEntity>>()
        showEp.onNext(DataMovies.generateEpisodes())
        return showEp.toFlowable(BackpressureStrategy.BUFFER)
    }

}