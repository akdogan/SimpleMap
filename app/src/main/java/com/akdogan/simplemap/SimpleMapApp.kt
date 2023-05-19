package com.akdogan.simplemap

import android.app.Application
import com.akdogan.simplemap.common.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class SimpleMapApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // check if api key was added
        val key = BuildConfig.FOURSQUARE_API_KEY
        if (key.isBlank()) throw IllegalStateException("Api key not found. Make sure to add it to the local.properties. See read.me for more info")

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