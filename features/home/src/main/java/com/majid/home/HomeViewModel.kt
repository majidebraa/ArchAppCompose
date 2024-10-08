package com.majid.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.majid.common.R
import com.majid.common.base.BaseViewModel
import com.majid.common.utils.Event
import com.majid.domain.usecase.home.GetTopUsersUseCase
import com.majid.domain.utils.AppDispatchers
import com.majid.domain.utils.Resource
import com.majid.model.User
import kotlinx.coroutines.launch
import com.majid.navigation.CustomNavDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

/**
 * A simple [BaseViewModel] that provide the data and handle logic to communicate with the model
 * for [HomeScreen].
 */
class HomeViewModel(
    private val getTopUsersUseCase: GetTopUsersUseCase,
    private val dispatchers: AppDispatchers,
    application: Application
) : BaseViewModel(application) {

    // FOR DATA
    private val _users = MutableStateFlow<Resource<List<User>>>(Resource.loading())
    val users: StateFlow<Resource<List<User>>> get() = _users

    init {
        getUsers()
    }

    // PUBLIC ACTIONS ---
    fun userClicksOnItem(user: User) {
        user.login?.let {
            navigate(CustomNavDirections.Detail(user.login!!).route)
        }
    }

    fun userRefreshesItems() = getUsers()

    // ---

    private fun getUsers() = viewModelScope.launch(dispatchers.main) {
        _users.emit(Resource.loading())
        //try {
        withContext(dispatchers.io) {
            getTopUsersUseCase().collectLatest {
                _users.emit(it)
                if (it.status == Resource.Status.ERROR) {
                    withContext(dispatchers.main) {
                        snackBarError.value = Event(R.string.an_error_happened)
                    }
                }

            }

        }
    }
}