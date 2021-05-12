package com.example.notflix.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.notflix.data.remote.config.ApiResponse
import com.example.notflix.data.remote.config.StatusResponse
import com.example.notflix.utils.AppExecutor
import com.example.notflix.values.ResourceData

abstract class NetworkBoundResource<RequestType,ResultType>(private val appExecutor: AppExecutor){

    private val result = MediatorLiveData<ResourceData<ResultType>>()

    init {
        result.value = ResourceData.loading(null)
        val dbSource = loadFromDB()
        result.addSource(dbSource){
            data -> result.removeSource(dbSource)
            if (shouldFetch(data)){
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource){
                    newData -> result.value = ResourceData.success(newData)
                }
            }
        }
    }

    private fun fetchFromNetwork(dataSource: LiveData<ResultType>){
        val apiResponse = createCall()

        result.addSource(dataSource){
            newData -> result.value = ResourceData.loading(newData)
        }

        result.addSource(apiResponse){
            dataFromApi ->
            result.removeSource(dataSource)
            result.removeSource(apiResponse)
            when(dataFromApi.responseStatus){
                StatusResponse.SUCCESS -> appExecutor.diskIO().execute{
                    saveCallResult(dataFromApi.responseBody)
                    appExecutor.mainThread().execute{
                        result.addSource(loadFromDB()){
                            newData -> result.value = ResourceData.success(newData)
                        }
                    }
                }
                StatusResponse.EMPTY -> appExecutor.mainThread().execute{
                    result.addSource(loadFromDB()){
                        newData -> result.value = ResourceData.success(newData)
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(loadFromDB()){
                        newData -> result.value = ResourceData.error(dataFromApi.responseMessage,newData)
                    }
                }
            }
        }
    }

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data : ResultType?): Boolean

    protected abstract fun saveCallResult(response: RequestType)

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun onFetchFailed()

    fun asLiveData() : LiveData<ResourceData<ResultType>> = result

}