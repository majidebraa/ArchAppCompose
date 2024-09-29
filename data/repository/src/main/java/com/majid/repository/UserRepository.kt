package com.majid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.majid.remote.error.ErrorHandler
import com.majid.domain.repositories.UserRepository
import com.majid.remote.UserDatasource
import com.majid.local.dao.UserDao
import com.majid.domain.utils.Resource
import com.majid.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val dataSource: UserDatasource,
    private val userDao: UserDao,
    private val handler: ErrorHandler
) :
    UserRepository {

    /**
     * Suspended function that will get a list of top [User]
     * via network (API).
     */
    override suspend fun getTopUsers(): LiveData<Resource<List<User>>> =

        liveData {
            emit(Resource.loading<List<User>>(null))
            try {
                val res = dataSource.fetchTopUsersAsync()
                emit(Resource.success(res.items))
            } catch (exception: Exception) {
                //exception.localizedMessage.
                val info = handler.handle(exception, true)

                emit(Resource.error<List<User>>(info.message!!, null))
            }

        }

    /**
     * Suspended function that will get a list of top [User]
     * whether in cache (SQLite) or via network (API).
     */

    override suspend fun getTopUsersWithCache(): Flow<Resource<List<User>>> = flow {
        // Emit the loading state first with cached data
        emit(
            Resource.loading()
        )

        try {
            // Fetch fresh data from the network
            val user = dataSource.fetchTopUsersAsync()

            // Update the database with the fetched data
            userDao.save(user.items)

            // Emit the success state with updated data
            emit(
                Resource.success(userDao.getTopUsers().first())
            )
        } catch (exception: Exception) {
            // Handle any errors, and emit the error state
            val info = handler.handle(exception, true)
            emit(
                Resource.error(info.message ?: "Unknown error")
            )
        }
    }



    /**
     * Suspended function that will get details of a [User]
     * whether in cache (SQLite) or via network (API).
     */

    override suspend fun getUserDetailWithCache(login: String): Flow<Resource<User>> =
        flow {
            emit(
                Resource.loading()
            )

            try {
                val user = dataSource.fetchUserDetailsAsync(login)

                // Update the database.
                userDao.save(user)
                // Re-establish the emission with success type.
                emit(
                    Resource.success(userDao.getUser(login).first())
                )
            } catch (exception: Exception) {
                // Any call to `emit` disposes the previous one automatically so we don't
                // need to dispose it here as we didn't get an updated value.
                val info = handler.handle(exception, true)
                emit(
                    Resource.error(info.message ?: "Unknown error")
                )
            }
        }
}