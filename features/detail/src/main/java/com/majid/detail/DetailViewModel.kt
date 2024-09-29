package com.majid.detail

import android.app.Application
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.majid.common.base.BaseViewModel
import com.majid.domain.usecase.detail.GetUserDetailUseCase
import com.majid.common.utils.Event
import com.majid.domain.utils.AppDispatchers
import com.majid.domain.utils.Resource
import com.majid.navigation.CustomNavDirections
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import com.majid.common.R
import com.majid.model.User
import kotlinx.coroutines.withContext


/**
 * A simple [BaseViewModel] that provide the data and handle logic to communicate with the model
 * for [DetailScreen].
 */
class DetailViewModel(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val dispatchers: AppDispatchers,
    private val argsLogin: String,
    application: Application
): BaseViewModel(application) {

    // PRIVATE DATA
    //private var userSource: LiveData<Resource<User>> = MutableLiveData()

    private val _user = MutableStateFlow <Resource<User>>(Resource.loading())
    val user: StateFlow<Resource<User>> get() = _user

    private val _isLoading = MutableStateFlow(Resource.Status.LOADING)
    val isLoading: StateFlow<Resource.Status> get() = _isLoading
    // PUBLIC ACTIONS ---
     init {
        getUserDetail()
    }

    fun reloadDataWhenUserRefreshes() = getUserDetail()

    fun userClicksOnAvatarImage(user: User) {
        user.avatarUrl?.let {
            navigate(CustomNavDirections.DetailImage(Uri.encode(it)).route)
        }

    }

    // ---

    private fun getUserDetail() = viewModelScope.launch(dispatchers.main) {

        _user.emit(Resource.loading())
        getUserDetailUseCase(login = argsLogin).collectLatest {
            _user.emit(it)
            if (it.status == Resource.Status.ERROR)
                withContext(dispatchers.main) {
                    snackBarError.value = Event(R.string.an_error_happened)
                }
        }

    }
}