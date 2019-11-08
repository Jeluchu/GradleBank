package com.jeluchu.gradlebank

import android.app.Application
import com.jeluchu.gradlebank.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GradleBank : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GradleBank)
            modules(listOf(
                networkModule,
                applicationModule,
                datasourceModule,
                useCaseModule,
                repositoryModule,
                viewModelModule))
        }
    }
}