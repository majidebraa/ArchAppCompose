package com.majid.domain.utils

sealed class Resource<out T>(val status: Status) {

    data class Success<out T>(val data: T) : Resource<T>(Status.SUCCESS)
    data class Error<out T>(val error: String, val data: T? = null) : Resource<T>(Status.ERROR)
    data class Loading<out T>(val data: T? = null) : Resource<T>(Status.LOADING)

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)

        fun <T> error(error: String, data: T? = null): Resource<T> = Error(error, data)

        fun <T> loading(data: T? = null): Resource<T> = Loading(data)
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}