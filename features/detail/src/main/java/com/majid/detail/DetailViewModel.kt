package com.majid.detail

import android.app.Application
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.majid.common.base.BaseViewModel
import com.majid.domain.usecase.detail.GetUserDetailUseCase
import com.majid.common.utils.Event
import com.majid.domain.utils.AppDispatchers
import com.majid.domain.utils.Resource
import com.majid.model.User
import com.majid.navigation.CustomNavDirections
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


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
            val encodedUrl = URLEncoder.encode(
                it,
                StandardCharsets.UTF_8.toString()
            )
            val encodedUrl2 = Uri.encode(it)
            navigate(CustomNavDirections.DetailImage(Uri.encode(it)).route)
        }

    }

    // ---

    private fun getUserDetail() = viewModelScope.launch(dispatchers.main) {


        _user.emit(Resource.loading())
        //try {
        getUserDetailUseCase(login = argsLogin).collectLatest {
            _user.emit(it)
            if (it.status == Resource.Status.ERROR) snackBarError.value = Event((it as Resource.Error).error)
        }
       // withContext(dispatchers.io) { userSource = getUserDetailUseCase(login = argsLogin) }
        /*_user.addSource(userSource) {
            _user.value = it.data
            _isLoading.value = it.status
            if (it.status == Resource.Status.ERROR) snackBarError.value = Event(R.string.an_error_happened)
        }*/
    }
}