package com.majid.home.di

import com.majid.domain.usecase.home.GetTopUsersUseCase
import com.majid.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    factory { GetTopUsersUseCase(get()) }
    viewModel { HomeViewModel(get(), get(), get ()) }
}