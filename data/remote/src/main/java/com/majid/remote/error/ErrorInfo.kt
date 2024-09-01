package com.majid.remote.error

import com.majid.remote.error.ErrorHandler


data class ErrorInfo(
    val error: Throwable,
    val type: ErrorHandler.ErrorType,
    val message: String?,
    val code: Int?
) {
    override fun toString(): String {
        return "ErrorInfo(error=$error, type=$type, message=$message, code=$code"
    }
}