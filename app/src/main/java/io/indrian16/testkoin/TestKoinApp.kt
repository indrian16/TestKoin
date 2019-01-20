package io.indrian16.testkoin

import android.app.Application
import io.indrian16.testkoin.di.listModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class TestKoinApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listModule)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}