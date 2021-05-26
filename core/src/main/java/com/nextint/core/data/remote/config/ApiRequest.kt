package com.nextint.core.data.remote.config

import com.nextint.core.BuildConfig
import com.nextint.core.data.remote.response.DetailMoviesResponse
import com.nextint.core.data.remote.response.DetailTvResponse
import com.nextint.core.data.remote.response.TrendingResponse
import com.nextint.core.data.remote.response.TvShowResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {

    @GET("trending/movie/day")
    fun getTrendingMovies(@Query("api_key") api_key : String=BuildConfig.API_KEY) : Flowable<TrendingResponse>

    @GET("tv/popular")
    fun getPopularTvShow(@Query("api_key") api_key: String=BuildConfig.API_KEY) : Flowable<TvShowResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movie_id : Int,
                       @Query("api_key") api_key: String=BuildConfig.API_KEY,
                       @Query("language") lang:String = "en-US") : Flowable<DetailMoviesResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(@Path("tv_id") tv_id : Int,
                       @Query("api_key") api_key: String=BuildConfig.API_KEY,
                       @Query("language") lang:String = "en-US") : Flowable<DetailTvResponse>
}