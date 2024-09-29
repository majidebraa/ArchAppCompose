package com.majid.domain.usecase.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.majid.domain.repositories.UserRepository
import com.majid.domain.utils.Resource
import com.majid.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Use case that gets a [Resource][List][UserEntity] from [UserRepository]
 * and makes some specific logic actions on it.
 *
 * In this Use Case, I'm just doing nothing... ¯\_(ツ)_/¯
 */
class GetTopUsersUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(): Flow<Resource<List<User>>> {
        return userRepository.getTopUsersWithCache()
            .map {
                it
            }

    }
}