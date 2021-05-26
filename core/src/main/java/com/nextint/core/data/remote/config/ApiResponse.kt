package com.nextint.core.data.remote.config

sealed class ApiResponse<out R> {
        data class success<out A>(val data : A) : ApiResponse<A>()

        object empty : ApiResponse<Nothing>()

        data class error(val message: String) : ApiResponse<Nothing>()
}