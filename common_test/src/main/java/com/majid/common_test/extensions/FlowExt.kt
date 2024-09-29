package com.majid.common_test.extensions

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow

fun <T> Flow<T>.blockingFirst(timeoutMillis: Long): T? {
    return runBlocking {
        try {
            withTimeout(timeoutMillis) {
                this@blockingFirst.first()
            }
        } catch (e: TimeoutCancellationException) {
            null // Return null if timeout happens
        }
    }
}