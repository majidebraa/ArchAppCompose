package com.majid.detail.di

import com.majid.detail.DetailImageViewModel
import com.majid.detail.DetailViewModel
import com.majid.domain.usecase.detail.GetUserDetailUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val featureDetailModule = module {
    factory { GetUserDetailUseCase(get()) }
    viewModel { (userId: String) -> DetailViewModel(get(), get(), userId,get()) }
    viewModel { params -> DetailImageViewModel(params.get(),get()) }
}
