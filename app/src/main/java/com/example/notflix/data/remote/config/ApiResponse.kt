package com.example.notflix.data.remote.config

class ApiResponse<T>(val responseStatus : StatusResponse, val responseBody : T, val responseMessage : String?) {
    companion object{
        fun <T> success(body : T) : ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body,null)

        fun <T> empty(message : String, body : T) : ApiResponse<T> = ApiResponse(StatusResponse.EMPTY,body,message)

        fun <T> error(message: String, body : T) : ApiResponse<T> = ApiResponse(StatusResponse.ERROR,body,message)
    }
}