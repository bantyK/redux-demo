package com.banty.reduxdemo3

import android.app.Application
import com.banty.reduxdemo3.module.appModule
import com.banty.reduxdemo3.module.presenterModule
import com.banty.reduxdemo3.module.reduxModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TodoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TodoApp)
            modules(
                listOf(
                    appModule,
                    reduxModule,
                    presenterModule
                )
            )
        }
    }
}