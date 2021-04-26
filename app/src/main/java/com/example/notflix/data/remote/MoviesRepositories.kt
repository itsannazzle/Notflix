package com.example.notflix.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notflix.data.remote.response.DetailMoviesResponse
import com.example.notflix.data.remote.response.DetailTvResponse
import com.example.notflix.data.remote.response.ResultsItem
import com.example.notflix.data.remote.response.TVResultsItem
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.PrevMoviesEntity
import com.example.notflix.entity.PrevTVEntity
import com.example.notflix.entity.TvShowEntity

class MoviesRepositories private constructor(private val remoteDataSource: RemoteDataSource) : NotflixDataSource{
    companion object{
        @Volatile
        private var instance : MoviesRepositories? = null
        fun getInstance(remoteDataSource: RemoteDataSource) : MoviesRepositories =
                instance ?: synchronized(this){
                    instance ?: MoviesRepositories(remoteDataSource).apply { instance = this }
                }
    }
    override fun getAllTrendingMovies(): LiveData<List<PrevMoviesEntity>> {
        val trendingMoviesResult = MutableLiveData<List<PrevMoviesEntity>>()
        remoteDataSource.getTrendingMovies(object : RemoteDataSource.TrendingCallback {
            override fun onTrendingMovies(trendingResponse: List<ResultsItem>) {
                val trendingList = ArrayList<PrevMoviesEntity>()
                for (response in trendingResponse){
                    val movie = PrevMoviesEntity(
                        response.id,
                        response.posterPath
                    )
                    trendingList.add(movie)
                }
                trendingMoviesResult.postValue(trendingList)
            }
        })
        return trendingMoviesResult
    }

    override fun getAllPopularTvShow(): LiveData<List<PrevTVEntity>> {
        val popularTvShow = MutableLiveData<List<PrevTVEntity>>()
        remoteDataSource.getPopularTvShow(object : RemoteDataSource.TvShowCallback {
            override fun onPopularTvShow(tvShowResponse: List<TVResultsItem>) {
                val popular = ArrayList<PrevTVEntity>()
                for (tvShow in tvShowResponse){
                    val show = PrevTVEntity(
                        tvShow.id,
                        tvShow.posterPath
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
                    val movie = MoviesEntity(
                            id_movies = id,
                            backdropPath,
                            poster = posterPath,
                            title = title,
                            genre = genres.forEach { it.name }.toString(),
                            country = productionCountries.forEach { it.name }.toString(),
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
                    for (genre in genres){
                        listGenre.add(genre.name)
                    }
                    val tvShow = TvShowEntity(id,
                            backdropPath,
                            posterPath,
                            name,
                            listGenre.toString(),
                            productionCountries.forEach { it.name }.toString(),
                            voteAverage,
                            overview,
                            episodeRunTime.get(0),
                            numberOfEpisodes
                    )
                    detailTvShowResult.postValue(tvShow)
                }

            }
        })
        return detailTvShowResult
    }
}