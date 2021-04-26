package com.example.notflix.data.remote.config

import com.example.notflix.BuildConfig
import com.example.notflix.data.remote.response.DetailMoviesResponse
import com.example.notflix.data.remote.response.DetailTvResponse
import com.example.notflix.data.remote.response.TrendingResponse
import com.example.notflix.data.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {

    @GET("trending/all/day")
    fun getTrendingMovies(@Query("api_key") api_key : String=BuildConfig.API_KEY) : Call<TrendingResponse>

    @GET("tv/popular")
    fun getPopularTvShow(@Query("api_key") api_key: String=BuildConfig.API_KEY) : Call<TvShowResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movie_id : Int,
                       @Query("api_key") api_key: String=BuildConfig.API_KEY,
                       @Query("language") lang:String = "en-US") : Call<DetailMoviesResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(@Path("tv_id") tv_id : Int,
                       @Query("api_key") api_key: String=BuildConfig.API_KEY,
                       @Query("language") lang:String = "en-US") : Call<DetailTvResponse>
}