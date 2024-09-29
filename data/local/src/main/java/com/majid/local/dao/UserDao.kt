package com.majid.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.majid.model.User
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao: BaseDao<User>() {

    @Query("SELECT * FROM User ORDER BY login ASC LIMIT 30")
    abstract  fun getTopUsers(): Flow<List<User>>

    @Query("SELECT * FROM User WHERE login = :login LIMIT 1")
    abstract  fun getUser(login: String): Flow<User>

    /**
     * Each time we save an user, we update its 'lastRefreshed' field
     * This allows us to know when we have to refresh its data
     */

    suspend fun save(user: User) {
        insert(user)
    }

    suspend fun save(users: List<User>) {
        insert(users)
    }
}