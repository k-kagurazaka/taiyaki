package com.kkagurazaka.taiyaki.sample

import android.app.Application
import com.kkagurazaka.taiyaki.Taiyaki

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(Taiyaki.LifeCycleCallbacks)
    }
}
