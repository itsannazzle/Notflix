package com.example.notflix

import android.app.Application
import com.example.notflix.di.usecaseModule
import com.example.notflix.di.viewModelModule
import com.nextint.core.di.databaseModule
import com.nextint.core.di.networkModule
import com.nextint.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    usecaseModule,
                    viewModelModule
                )
            )
        }
    }
}