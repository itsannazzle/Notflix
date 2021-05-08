package com.example.notflix.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.notflix.data.NetworkBoundResource
import com.example.notflix.data.local.LocalDataSource
import com.example.notflix.data.local.entity.EpisodesEntity
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.data.remote.response.*
import com.example.notflix.utils.DataMovies
import com.example.notflix.values.ResourceData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoviesRepositories private constructor(private val remoteDataSource: RemoteDataSource,
private val localDataSource: LocalDataSource) : NotflixDataSource{
    companion object{
        @Volatile
        private var instance : MoviesRepositories? = null
        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) : MoviesRepositories =
                instance ?: synchronized(this){
                    instance ?: MoviesRepositories(remoteDataSource,localDataSource).apply { instance = this }
                }
    }

    override fun getAllTrendingMovies(): LiveData<ResourceData<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<List<ResultsItem>, PagedList<MoviesEntity>>() {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config =PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(3)
                        .setPageSize(3)
                        .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(),config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(response: List<ResultsItem>) {
                val movieList = ArrayList<MoviesEntity>()
                for (respon in response){
                    with(respon){
                        val movie = MoviesEntity(
                                id,posterPath
                        )
                        movieList.add(movie)
                    }
                }
                GlobalScope.launch(Dispatchers.IO){
                    localDataSource.insertMovie(movieList)
                }
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getTrendingMovies()
            }

            override fun onFetchFailed() {
                Log.i("MoviesRepository","Fetch trending data movies failed")
            }
        }.asLiveData()
    }

    override fun getAllPopularTvShow(): LiveData<ResourceData<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TVResultsItem>, PagedList<TvShowEntity>>() {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config =PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(3)
                        .setPageSize(3)
                        .build()
                return LivePagedListBuilder(localDataSource.getAllTvShow(),config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(response: List<TVResultsItem>) {
                val tvshowList = ArrayList<TvShowEntity>()
                for (respon in response){
                    with(respon){
                        val tvshow = TvShowEntity(
                                id,posterPath
                        )
                        tvshowList.add(tvshow)
                    }
                }
                GlobalScope.launch(Dispatchers.IO){
                    localDataSource.insertTvShow(tvshowList)
                }
            }

            override fun createCall(): LiveData<ApiResponse<List<TVResultsItem>>> {
                return remoteDataSource.getPopularTvShow()
            }

            override fun onFetchFailed() {
                Log.i("MoviesRepository","Fetch popula tvshow data failed")
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movie_id: Int): LiveData<ResourceData<MoviesEntity>> {
        return object : NetworkBoundResource<List<ResultsItem>, MoviesEntity>() {

            override fun loadFromDB(): LiveData<MoviesEntity> {
                return localDataSource.getSelectedMovie(movie_id)
            }

            override fun shouldFetch(data: MoviesEntity?): Boolean {
                return data == null
            }

            override fun saveCallResult(response: List<ResultsItem>) {
                val detailMoveie = ArrayList<MoviesEntity>()
                val listGenre = ArrayList<String>()
                    val listCountry = ArrayList<String>()
                for (respon in response){
                    with(respon){
                        if (genreIds != null) {
                            for (genre in genreIds){
                                listGenre.add(genre.)
                            }
                        }
                        for (country in productionCountries){
                            listCountry.add(country.name)
                        }
                        val movie = MoviesEntity(
                                id,posterPath,posterPath,title,genreIds,originCountry,voteAverage,overview,
                        )
                        movieList.add(movie)
                    }
                }
                GlobalScope.launch(Dispatchers.IO){
                    localDataSource.insertMovie(movieList)
                }
            }

            override fun createCall(): LiveData<ApiResponse<List<DetailMoviesResponse>>> {
                TODO("Not yet implemented")
            }

            override fun onFetchFailed() {
                TODO("Not yet implemented")
            }
        }.asLiveData()
    }

    override fun getDetailTv(tv_id: Int): LiveData<ResourceData<TvShowEntity>> {
        TODO("Not yet implemented")
    }
//    override fun getAllTrendingMovies(): LiveData<List<MoviesEntity>> {
//        val trendingMoviesResult = MutableLiveData<List<MoviesEntity>>()
//        remoteDataSource.getTrendingMovies(object : RemoteDataSource.TrendingCallback {
//            override fun onTrendingMovies(trendingResponse: List<ResultsItem>) {
//                val trendingList = ArrayList<MoviesEntity>()
//                for (response in trendingResponse){
//                    val movie = MoviesEntity(
//                        poster = response.posterPath,
//                            id_movies = response.id
//                    )
//                    trendingList.add(movie)
//                }
//                trendingMoviesResult.postValue(trendingList)
//            }
//        })
//        return trendingMoviesResult
//    }
//
//    override fun getAllPopularTvShow(): LiveData<List<TvShowEntity>> {
//        val popularTvShow = MutableLiveData<List<TvShowEntity>>()
//        remoteDataSource.getPopularTvShow(object : RemoteDataSource.TvShowCallback {
//            override fun onPopularTvShow(tvShowResponse: List<TVResultsItem>) {
//                val popular = ArrayList<TvShowEntity>()
//                for (tvShow in tvShowResponse){
//                    val show = TvShowEntity(
//                       poster = tvShow.posterPath,
//                            id_tvshow = tvShow.id
//                    )
//                    popular.add(show)
//                }
//                popularTvShow.postValue(popular)
//            }
//        })
//        return popularTvShow
//    }
//
//    override fun getDetailMovie(movie_id: Int): LiveData<MoviesEntity> {
//        val detailMovieResult = MutableLiveData<MoviesEntity>()
//        remoteDataSource.getDetailMovie(movie_id, object : RemoteDataSource.DetailMovieCallback {
//            override fun onDetailMovies(detailMoviesResponse: DetailMoviesResponse) {
//                with(detailMoviesResponse){
//                    val listGenre = ArrayList<String>()
//                    val listCountry = ArrayList<String>()
//                    for (genre in genres){
//                        listGenre.add(genre.name)
//                    }
//                    for (country in productionCountries){
//                        listCountry.add(country.name)
//                    }
//                    val movie = MoviesEntity(
//                            id_movies = id,
//                            backdropPath,
//                            poster = posterPath,
//                            title = title,
//                            genre = listGenre.toString(),
//                            country = listCountry.toString(),
//                            rating = voteAverage,
//                            overview = overview,
//                            duration = runtime
//                    )
//
//                    detailMovieResult.postValue(movie)
//                }
//
//            }
//        })
//        return detailMovieResult
//    }
//
//    override fun getDetailTv(tv_id: Int): LiveData<TvShowEntity> {
//        val detailTvShowResult = MutableLiveData<TvShowEntity>()
//        remoteDataSource.getDetailTvShow(tv_id, object : RemoteDataSource.DetailTvSHowCallback {
//            override fun onDetailTvShow(detailTvResponse: DetailTvResponse) {
//                with(detailTvResponse){
//                    val listGenre = ArrayList<String>()
//                    val listCountry = ArrayList<String>()
//                    for (genre in genres){
//                        listGenre.add(genre.name)
//                    }
//                    for (country in productionCountries){
//                        listCountry.add(country.name)
//                    }
//                    if (episodeRunTime.isNotEmpty()){
//                        val tvShow = TvShowEntity(
//                                id,
//                                backdropPath,
//                                posterPath,
//                                name,
//                                listGenre.toString(),
//                                listCountry.toString(),
//                                voteAverage,
//                                overview,
//                                episodeRunTime[0],
//                                numberOfEpisodes
//                        )
//                        detailTvShowResult.postValue(tvShow)
//                    }
//                }
//            }
//        })
//        return detailTvShowResult
//    }

    override fun getEpisodes(): LiveData<List<EpisodesEntity>> {
        val showEp = MutableLiveData<List<EpisodesEntity>>()
        showEp.postValue(DataMovies.generateEpisodes())
        return showEp
    }
}