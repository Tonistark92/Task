package com.iscoding.mytask.util

import android.app.Application
import com.iscoding.mytask.di.koin.appModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.fileProperties

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            //ex
//            fileProperties("application.properties")
            // ex
            properties(mapOf(
                "api.url" to "https://api.example.com",
                "api.key" to "my_api_key"
            ))
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        // Optionally stop Koin when the application terminates
        stopKoin()
    }

}