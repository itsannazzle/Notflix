package com.nextint.core.values

sealed class ResourceData<T>(val data: T? = null, val message: String? = null) {
        class Success<T>(data: T): ResourceData<T>(data)

        class Error<T>(msg: String, data: T? = null): ResourceData<T>(data,msg)

        class Loading<T>(data: T? = null): ResourceData<T>(data)

}