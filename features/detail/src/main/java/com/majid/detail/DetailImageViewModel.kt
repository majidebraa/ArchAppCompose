package com.majid.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.majid.common.base.BaseViewModel

/**
 * A simple [BaseViewModel] that provide the data and handle logic to communicate with the model
 * for [DetailImageFragment].
 */
class DetailImageViewModel(argsImageUrl: String, application: Application): BaseViewModel(
    application
) {

    // PRIVATE DATA
    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> get() = _imageUrl

    // PUBLIC ACTIONS ---
    init {
        _imageUrl.value = argsImageUrl
    }
}