package com.nextint.core.data


import com.nextint.core.data.remote.config.ApiResponse
import com.nextint.core.values.ResourceData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

abstract class NetworkBoundResource<RequestType,ResultType>{

    private val result = PublishSubject.create<ResourceData<ResultType>>()
    private val compositeDisposable = CompositeDisposable()

    init {

        val dbSource = loadFromDB()
        val db = dbSource
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe { dbValue ->
                dbSource.unsubscribeOn(Schedulers.io())
                if (shouldFetch(dbValue)) {
                    fetchFromNetwork()
                } else {
                    result.onNext(ResourceData.Success(dbValue))
                }
            }
        compositeDisposable.add(db)
    }

    private fun fetchFromNetwork(){
        val apiResponse = createCall()

        result.onNext(ResourceData.Loading(null))
        val response = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .subscribe {
                responseResult ->
                when(responseResult){
                    is ApiResponse.Success -> {
                        saveCallResult(responseResult.data)
                        val dbSource = loadFromDB()
                        dbSource.subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(1)
                            .subscribe {
                                dbSource.unsubscribeOn(Schedulers.io())
                                result.onNext(ResourceData.Success(it))
                            }
                    }
                    is ApiResponse.Empty -> {
                        val dbSource = loadFromDB()
                        dbSource.subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(1)
                            .subscribe {
                                dbSource.unsubscribeOn(Schedulers.io())
                                result.onNext(ResourceData.Success(it))
                            }
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        result.onNext(ResourceData.Error(responseResult.message,null))
                    }
                }
            }
        compositeDisposable.add(response)
    }

    protected abstract fun loadFromDB(): Flowable<ResultType>

    protected abstract fun shouldFetch(data : ResultType?): Boolean

    protected abstract fun saveCallResult(response: RequestType)

    protected abstract fun createCall(): Flowable<ApiResponse<RequestType>>

    protected abstract fun onFetchFailed()

    fun asFlowable() : Flowable<ResourceData<ResultType>> = result.toFlowable(BackpressureStrategy.BUFFER)

}