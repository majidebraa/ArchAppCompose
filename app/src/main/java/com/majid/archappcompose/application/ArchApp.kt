package com.majid.archappcompose.application

import android.app.Application
import com.majid.archappcompose.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

open class ArchApp :  Application() {

    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    // CONFIGURATION ---
    open fun configureDi() = startKoin {
        androidContext(this@ArchApp)
        androidLogger()
        modules(provideComponent())
    }

    // PUBLIC API ---
    open fun provideComponent() = appComponent


}