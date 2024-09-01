package com.majid.remote.error

import com.majid.model.ErrorMessage
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandlerImpl(val retrofit: Retrofit) : ErrorHandler {
    override fun handle(error: Throwable, enable: Boolean): ErrorInfo {
        var info = ErrorInfo(error, ErrorHandler.ErrorType.INTERNAL, null, null)
        error.printStackTrace()
        if (error is HttpException) {
            when {
                error.code() == 401 -> {
                    info = info.copy(type = ErrorHandler.ErrorType.UNAUTHORIZED, message = "دسترسی غیر مجاز")
                }
                error.code() == 403 -> {
                    info = info.copy(type = ErrorHandler.ErrorType.FORBIDDEN, message = "دوباره لاگین کنید")
                }
                error.code() == 422 -> {
                    info = info.copy(type = ErrorHandler.ErrorType.INTERNAL_SERVER_422, message = "خطا در سرور")
                }
                error.code() == 500 -> {
                    info = info.copy(type = ErrorHandler.ErrorType.INTERNAL_SERVER, message = "خطا در سرور")
                }
                error.code() == 503 -> {
                    info = info.copy(type = ErrorHandler.ErrorType.INTERNAL_SERVER, message = "خطا در سرور")
                }
                else -> {
                    info = info.copy(type = ErrorHandler.ErrorType.NOT_AN_ERROR)
                }
            }
            if (error is UnknownHostException
                || error is SocketTimeoutException
                || error is ConnectException
            ) {
                info = info.copy(type = ErrorHandler.ErrorType.NETWORK, message = "خطا در ارتباط با سرور")
            }
            error.response()?.errorBody()?.let { responseBody ->
                try {
                    val responseError: ErrorMessage? =
                        retrofit.responseBodyConverter<ErrorMessage>(
                            ErrorMessage::class.java,
                            ErrorMessage::class.java.annotations).convert(responseBody)
                    var message  = ""
                    responseError?.errors?.onEach { x -> message += x.error }
                    info = info.copy(message =  message , code = responseError?.status)

                    if (info.type == ErrorHandler.ErrorType.FORBIDDEN && (info.code == 2007 || info.code == 2002)) {
                        info = info.copy(type = ErrorHandler.ErrorType.FORBIDDEN_PIN)
                    } else if (info.type == ErrorHandler.ErrorType.UNAUTHORIZED && info.code == 2008) {
                        info = info.copy(type = ErrorHandler.ErrorType.UNAUTHORIZED_PIN)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    //ignored
                }

            }

        } else if (error is UnknownHostException || error is SocketTimeoutException || error is ConnectException) {
            info = info.copy(type = ErrorHandler.ErrorType.NETWORK, message = "خطا در ارتباط با سرور")
        }

        return info
    }

}