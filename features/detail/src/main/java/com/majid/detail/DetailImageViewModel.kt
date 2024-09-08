package com.majid.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.majid.common.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * A simple [BaseViewModel] that provide the data and handle logic to communicate with the model
 * for [DetailImageScreen].
 */
class DetailImageViewModel(argsImageUrl: String, application: Application): BaseViewModel(
    application
) {

    // PRIVATE DATA
    private val _imageUrl = MutableStateFlow(argsImageUrl)
    val imageUrl: StateFlow<String?> get() = _imageUrl

    // PUBLIC ACTIONS ---
    init {
        _imageUrl.value = argsImageUrl
    }
}