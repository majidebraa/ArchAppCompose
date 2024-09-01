package com.majid.common.utils
import androidx.compose.runtime.*

class Event<T>(private val content: T) {
    private var hasBeenHandled by mutableStateOf(false)

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}