package com.nextint.core.data.remote

import android.annotation.SuppressLint
import android.util.Log
import com.nextint.core.data.remote.config.ApiRequest
import com.nextint.core.data.remote.config.ApiResponse
import com.nextint.core.data.remote.response.DetailMoviesResponse
import com.nextint.core.data.remote.response.DetailTvResponse
import com.nextint.core.data.remote.response.ResultsItem
import com.nextint.core.data.remote.response.TVResultsItem
import com.nextint.core.utils.IdlingResource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

@SuppressLint("CheckResult")
class RemoteDataSource(private val apiRequest: ApiRequest) {

    fun getTrendingMovies() : Flowable<ApiResponse<List<ResultsItem>>>{
        val result = PublishSubject.create<ApiResponse<List<ResultsItem>>>()
        IdlingResource.increment()
        val client = apiRequest.getTrendingMovies()
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({
                response ->
                val responResult = response.results
                result.onNext(
                    if (responResult.isNotEmpty()) ApiResponse.Success(responResult) else ApiResponse.Empty)
                        },{
                    error ->result.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }


    fun getPopularTvShow() : Flowable<ApiResponse<List<TVResultsItem>>> {
        val result = PublishSubject.create<ApiResponse<List<TVResultsItem>>>()
        IdlingResource.increment()
        apiRequest.getPopularTvShow()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                response ->
                result.onNext(
                    if (response.results.isNotEmpty()) ApiResponse.Success(response.results) else ApiResponse.Empty
                )
            },{
                error -> result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }


    fun getDetailMovie(movie_id : Int) : Flowable<ApiResponse<DetailMoviesResponse>>{
        val result = PublishSubject.create<ApiResponse<DetailMoviesResponse>>()
        IdlingResource.increment()
        apiRequest.getDetailMovie(movie_id)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                response ->
                result.onNext(ApiResponse.Success(response))
                Log.e("Api", response.title.toString())
            }, {
                error -> result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }


    fun getDetailTvShow(tv_id : Int) : Flowable<ApiResponse<DetailTvResponse>> {
        val result = PublishSubject.create<ApiResponse<DetailTvResponse>>()
        IdlingResource.increment()
        apiRequest.getDetailTvShow(tv_id)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({
                response -> result.onNext(ApiResponse.Success(response))
            },{
                error -> result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

}