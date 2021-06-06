package com.nextint.core.data.remote.config

sealed class ApiResponse<out R> {
        data class Success<out A>(val data : A) : ApiResponse<A>()

        object Empty : ApiResponse<Nothing>()

        data class Error(val message: String) : ApiResponse<Nothing>()
}