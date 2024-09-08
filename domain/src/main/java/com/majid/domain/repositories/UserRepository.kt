package com.majid.domain.repositories

import androidx.lifecycle.LiveData
import com.majid.domain.utils.Resource
import com.majid.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun getTopUsers(): LiveData<Resource<List<User>>>
    suspend fun getTopUsersWithCache(): Flow<Resource<List<User>>>
    suspend fun getUserDetailWithCache(login: String): Flow<Resource<User>>
}