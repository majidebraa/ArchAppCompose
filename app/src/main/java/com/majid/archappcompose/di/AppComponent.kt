package com.majid.archappcompose.di

import com.majid.remote.di.createRemoteModule
import com.majid.repository.di.repositoryModule
import com.majid.home.di.featureHomeModule
import com.majid.detail.di.featureDetailModule
import com.majid.local.di.localModule
val appComponent= listOf(
    createRemoteModule("https://api.github.com/"),
    repositoryModule,
    featureHomeModule,
    featureDetailModule,
    localModule
)

