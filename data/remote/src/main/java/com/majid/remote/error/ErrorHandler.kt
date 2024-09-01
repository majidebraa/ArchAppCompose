package com.majid.remote.error

interface ErrorHandler {
    enum class ErrorType {
        NOT_AN_ERROR,
        UNAUTHORIZED,
        FORBIDDEN,
        INTERNAL_SERVER_422,
        INTERNAL_SERVER,
        NETWORK,
        INTERNAL,
        UNAUTHORIZED_PIN,
        FORBIDDEN_PIN
    }

    fun handle(error: Throwable, enable: Boolean): ErrorInfo

}