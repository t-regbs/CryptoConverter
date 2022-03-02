package com.timilehinaregbesola.cryptoconverter

import android.app.Application
import com.timilehinaregbesola.cryptoconverter.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CryptoConverterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@CryptoConverterApplication)
            modules(listOf(appModule))
        }
    }
}
