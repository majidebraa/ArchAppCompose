package com.majid.repository.di

import com.majid.domain.repositories.UserRepository
import com.majid.domain.utils.AppDispatchers
import com.majid.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val repositoryModule = module {
    factory {
        AppDispatchers(
            Dispatchers.Main,
            Dispatchers.IO
        )
    }
    factory { UserRepositoryImpl(
        get(),
        get(),
        get()
    ) as UserRepository
    }
}