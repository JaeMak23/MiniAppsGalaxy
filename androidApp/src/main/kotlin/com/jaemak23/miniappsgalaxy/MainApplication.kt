package com.jaemak23.miniappsgalaxy

import android.app.Application
import com.jaemak23.miniappsgalaxy.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
        }
    }
}