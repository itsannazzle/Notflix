package com.example.notflix.data.remote

import android.util.Log
import com.example.notflix.data.remote.config.ApiConfig
import com.example.notflix.data.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
   // private val handler = Handler(Looper.getMainLooper()) nanti dulu
    companion object{
        @Volatile
        private var instance : RemoteDataSource? = null
        fun getInstance() : RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getTrendingMovies(callback: TrendingCallback){
        ApiConfig.getApiService().getTrendingMovies().enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(call: Call<TrendingResponse>, response: Response<TrendingResponse>) {
                callback.onTrendingMovies(response.body()?.results as List<ResultsItem>)
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                Log.d(RemoteDataSource::class.java.simpleName,"Failed getTrendingMovies, cause : ${t.message}")
            }

        })
    }

    interface TrendingCallback {
        fun onTrendingMovies(trendingResponse: List<ResultsItem>)
    }

    fun getPopularTvShow(callback : TvShowCallback){
        ApiConfig.getApiService().getPopularTvShow().enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                callback.onPopularTvShow(response.body()?.results as List<TVResultsItem> )
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.d(RemoteDataSource::class.java.simpleName,"Failed getPopularTvShow, cause : ${t.message}")
            }

        })
    }

    interface TvShowCallback {
        fun onPopularTvShow(tvShowResponse: List<TVResultsItem>)
    }

    fun getDetailMovie(movie_id : Int, callback : DetailMovieCallback){
        ApiConfig.getApiService().getDetailMovie(movie_id).enqueue(object : Callback<DetailMoviesResponse> {
            override fun onResponse(call: Call<DetailMoviesResponse>, response: Response<DetailMoviesResponse>) {
                callback.onDetailMovies(response.body() as DetailMoviesResponse)
            }

            override fun onFailure(call: Call<DetailMoviesResponse>, t: Throwable) {
                Log.d(RemoteDataSource::class.java.simpleName,"Failed getDetailMovie, cause : ${t.message}")
            }

        })
    }

    interface DetailMovieCallback {
        fun onDetailMovies(detailMoviesResponse: DetailMoviesResponse)
    }

    fun getDetailTvShow(tv_id : Int, callback : DetailTvSHowCallback){
        ApiConfig.getApiService().getDetailTvShow(tv_id).enqueue(object : Callback<DetailTvResponse> {
            override fun onResponse(call: Call<DetailTvResponse>, response: Response<DetailTvResponse>) {
                callback.onDetailTvShow(response.body() as DetailTvResponse)
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                Log.d(RemoteDataSource::class.java.simpleName,"Failed getDetailTvShow, cause : ${t.message}")
            }

        })
    }

    interface DetailTvSHowCallback {
        fun onDetailTvShow(detailTvResponse: DetailTvResponse)
    }
}