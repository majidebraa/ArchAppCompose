package com.majid.common.base

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.majid.common.utils.Event
import com.majid.navigation.NavigationCommand
import androidx.lifecycle.AndroidViewModel


abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> get() = _navigation


    // FOR ERROR HANDLER
    val snackBarError = MutableLiveData<Event<Int>>()

    fun navigate(directions: String) {
        _navigation.postValue(Event(NavigationCommand.To(directions)))
    }

    fun navigateBack() {
        _navigation.postValue(Event(NavigationCommand.Back))
    }

    /*fun showSnackBar(message: String) {
        _snackBarError.postValue(Event(message))
    }*/
}