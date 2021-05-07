package com.example.notflix.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notflix.data.remote.response.DetailMoviesResponse
import com.example.notflix.data.remote.response.DetailTvResponse
import com.example.notflix.data.remote.response.ResultsItem
import com.example.notflix.data.remote.response.TVResultsItem
import com.example.notflix.data.local.entity.EpisodesEntity
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.utils.DataMovies

class MoviesRepositories private constructor(private val remoteDataSource: RemoteDataSource) : NotflixDataSource{
    companion object{
        @Volatile
        private var instance : MoviesRepositories? = null
        fun getInstance(remoteDataSource: RemoteDataSource) : MoviesRepositories =
                instance ?: synchronized(this){
                    instance ?: MoviesRepositories(remoteDataSource).apply { instance = this }
                }
    }
    override fun getAllTrendingMovies(): LiveData<List<MoviesEntity>> {
        val trendingMoviesResult = MutableLiveData<List<MoviesEntity>>()
        remoteDataSource.getTrendingMovies(object : RemoteDataSource.TrendingCallback {
            override fun onTrendingMovies(trendingResponse: List<ResultsItem>) {
                val trendingList = ArrayList<MoviesEntity>()
                for (response in trendingResponse){
                    val movie = MoviesEntity(
                        poster = response.posterPath,
                            id_movies = response.id
                    )
                    trendingList.add(movie)
                }
                trendingMoviesResult.postValue(trendingList)
            }
        })
        return trendingMoviesResult
    }

    override fun getAllPopularTvShow(): LiveData<List<TvShowEntity>> {
        val popularTvShow = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getPopularTvShow(object : RemoteDataSource.TvShowCallback {
            override fun onPopularTvShow(tvShowResponse: List<TVResultsItem>) {
                val popular = ArrayList<TvShowEntity>()
                for (tvShow in tvShowResponse){
                    val show = TvShowEntity(
                       poster = tvShow.posterPath,
                            id_tvshow = tvShow.id
                    )
                    popular.add(show)
                }
                popularTvShow.postValue(popular)
            }
        })
        return popularTvShow
    }

    override fun getDetailMovie(movie_id: Int): LiveData<MoviesEntity> {
        val detailMovieResult = MutableLiveData<MoviesEntity>()
        remoteDataSource.getDetailMovie(movie_id, object : RemoteDataSource.DetailMovieCallback {
            override fun onDetailMovies(detailMoviesResponse: DetailMoviesResponse) {
                with(detailMoviesResponse){
                    val listGenre = ArrayList<String>()
                    val listCountry = ArrayList<String>()
                    for (genre in genres){
                        listGenre.add(genre.name)
                    }
                    for (country in productionCountries){
                        listCountry.add(country.name)
                    }
                    val movie = MoviesEntity(
                            id_movies = id,
                            backdropPath,
                            poster = posterPath,
                            title = title,
                            genre = listGenre.toString(),
                            country = listCountry.toString(),
                            rating = voteAverage,
                            overview = overview,
                            duration = runtime
                    )

                    detailMovieResult.postValue(movie)
                }

            }
        })
        return detailMovieResult
    }

    override fun getDetailTv(tv_id: Int): LiveData<TvShowEntity> {
        val detailTvShowResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getDetailTvShow(tv_id, object : RemoteDataSource.DetailTvSHowCallback {
            override fun onDetailTvShow(detailTvResponse: DetailTvResponse) {
                with(detailTvResponse){
                    val listGenre = ArrayList<String>()
                    val listCountry = ArrayList<String>()
                    for (genre in genres){
                        listGenre.add(genre.name)
                    }
                    for (country in productionCountries){
                        listCountry.add(country.name)
                    }
                    if (episodeRunTime.isNotEmpty()){
                        val tvShow = TvShowEntity(
                                id,
                                backdropPath,
                                posterPath,
                                name,
                                listGenre.toString(),
                                listCountry.toString(),
                                voteAverage,
                                overview,
                                episodeRunTime[0],
                                numberOfEpisodes
                        )
                        detailTvShowResult.postValue(tvShow)
                    }
                }
            }
        })
        return detailTvShowResult
    }

    override fun getEpisodes(): LiveData<List<EpisodesEntity>> {
        val showEp = MutableLiveData<List<EpisodesEntity>>()
        showEp.postValue(DataMovies.generateEpisodes())
        return showEp
    }
}