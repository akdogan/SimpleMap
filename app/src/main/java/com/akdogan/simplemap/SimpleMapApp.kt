package com.akdogan.simplemap

import android.app.Application
import com.akdogan.simplemap.common.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class SimpleMapApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initiate DI
        startKoin {
            androidContext(this@SimpleMapApp)
            modules(appModule)
        }

        // Initiate Logger
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}