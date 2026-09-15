package com.jaemak23.miniappsgalaxy

import android.app.Application
import com.jaemak23.miniappsgalaxy.di.initKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}