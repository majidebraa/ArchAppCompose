package com.majid.domain.utils

sealed class Resource<out T> {

    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val error: String, val data: T? = null) : Resource<T>()
    data class Loading<out T>(val data: T? = null) : Resource<T>()

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)

        fun <T> error(error: String, data: T? = null): Resource<T> = Error(error, data)

        fun <T> loading(data: T? = null): Resource<T> = Loading(data)
    }
}