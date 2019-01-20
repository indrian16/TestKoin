package io.indrian16.testkoin

import android.app.Application
import timber.log.Timber

class TestKoinApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}