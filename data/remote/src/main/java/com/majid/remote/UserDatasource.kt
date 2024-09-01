package com.majid.remote

/**
 * Implementation of [UserService] interface
 */
class UserDatasource(private val userService: UserService) {

    suspend fun fetchTopUsersAsync() =
            userService.fetchTopUsersAsync()

    suspend fun fetchUserDetailsAsync(login: String) =
            userService.fetchUserDetailsAsync(login)
}