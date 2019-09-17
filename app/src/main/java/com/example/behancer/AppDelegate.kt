package com.example.behancer

import android.app.Application
import com.example.behancer.di.AppComponent
import com.example.behancer.di.AppModule
import com.example.behancer.di.DaggerAppComponent
import com.example.behancer.di.NetworkModule

class AppDelegate : Application() {

    companion object {
        private lateinit var INSTANCE: AppDelegate
        @JvmStatic
        fun getInjector(): AppDelegate = INSTANCE
    }

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }

    fun getAppComponent() = appComponent
}
