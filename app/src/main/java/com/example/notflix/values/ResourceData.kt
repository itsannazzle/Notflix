package com.example.notflix.values

sealed class ResourceData<T>(val data: T? = null, val message: String? = null) {
        class success<T>(data: T): ResourceData<T>(data)

        class error<T>(msg: String, data: T? = null): ResourceData<T>(data,msg)

        class loading<T>(data: T? = null): ResourceData<T>(data)

}