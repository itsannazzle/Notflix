package com.example.notflix.values

data class ResourceData<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ResourceData<T> = ResourceData(Status.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): ResourceData<T> = ResourceData(Status.ERROR, data, msg)

        fun <T> loading(data: T?): ResourceData<T> = ResourceData(Status.LOADING, data, null)
    }
}