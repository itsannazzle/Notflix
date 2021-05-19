package com.example.notflix.data.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notflix.data.remote.config.ApiConfig
import com.example.notflix.data.remote.config.ApiResponse
import com.example.notflix.data.remote.response.*
import com.example.notflix.utils.IdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource() {
   private val handler = Handler(Looper.getMainLooper())
    companion object{
        @Volatile
        private var instance : RemoteDataSource? = null
        fun getInstance() : RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getTrendingMovies() : LiveData<ApiResponse<List<ResultsItem>>>{
        val result = MutableLiveData<ApiResponse<List<ResultsItem>>>()
        IdlingResource.increment()
        ApiConfig.getApiService().getTrendingMovies().enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(call: Call<TrendingResponse>, response: Response<TrendingResponse>) {
                if (response.isSuccessful){
                    handler.post {
                        result.value = response.body()?.let { ApiResponse.success(it.results) }
                        Log.d("Respon", response.code().toString())
                        if (!IdlingResource.getEspressoIdlingResource().isIdleNow){
                            IdlingResource.decrement()
                        }
                    }
                } else {
                    IdlingResource.decrement()
                    ApiResponse.error(response.errorBody().toString(),null)
                }

            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                Log.d(RemoteDataSource::class.java.simpleName,"Failed getTrendingMovies, cause : ${t.message}")
            }

        })

        return result
    }


    fun getPopularTvShow() : LiveData<ApiResponse<List<TVResultsItem>>> {
        val result = MutableLiveData<ApiResponse<List<TVResultsItem>>>()
        IdlingResource.increment()
        ApiConfig.getApiService().getPopularTvShow().enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                handler.post {
                    result.value = response.body()?.let { ApiResponse.success(it.results) }
                    Log.d("Respon", response.code().toString())
                if (!IdlingResource.getEspressoIdlingResource().isIdleNow){
                    IdlingResource.decrement()
                }
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.d(RemoteDataSource::class.java.simpleName,"Failed getPopularTvShow, cause : ${t.message}")
            }

        })
        return result
    }


    fun getDetailMovie(movie_id : Int) : LiveData<ApiResponse<DetailMoviesResponse>>{
        val result = MutableLiveData<ApiResponse<DetailMoviesResponse>>()
        IdlingResource.increment()
        ApiConfig.getApiService().getDetailMovie(movie_id).enqueue(object : Callback<DetailMoviesResponse> {
            override fun onResponse(call: Call<DetailMoviesResponse>, response: Response<DetailMoviesResponse>) {
               handler.post {  result.value = ApiResponse.success(response.body() as DetailMoviesResponse)
                   Log.d("Respon", response.code().toString())
               if (!IdlingResource.getEspressoIdlingResource().isIdleNow){
                   IdlingResource.decrement()
               }
               }
            }

            override fun onFailure(call: Call<DetailMoviesResponse>, t: Throwable) {
                Log.d(RemoteDataSource::class.java.simpleName,"Failed getDetailMovie, cause : ${t.message}")
            }

        })

        return result
    }


    fun getDetailTvShow(tv_id : Int) : LiveData<ApiResponse<DetailTvResponse>> {
        val result = MutableLiveData<ApiResponse<DetailTvResponse>>()
        IdlingResource.increment()
        ApiConfig.getApiService().getDetailTvShow(tv_id).enqueue(object : Callback<DetailTvResponse> {
            override fun onResponse(call: Call<DetailTvResponse>, response: Response<DetailTvResponse>) {
               handler.post {  result.value = ApiResponse.success(response.body() as DetailTvResponse)
                   Log.d("Respon", response.code().toString())
               if (!IdlingResource.getEspressoIdlingResource().isIdleNow){
                   IdlingResource.decrement()
               }}
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                Log.d(RemoteDataSource::class.java.simpleName,"Failed getDetailTvShow, cause : ${t.message}")
            }

        })

        return result
    }

}