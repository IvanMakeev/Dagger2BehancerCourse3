package com.example.behancer

import android.app.Application
import com.example.behancer.di.*

class AppDelegate : Application() {

    companion object {
        private lateinit var INSTANCE: AppDelegate
        @JvmStatic
        fun getInjector(): AppDelegate = INSTANCE
    }

    private lateinit var appComponent: AppComponent
    private var fragmentComponent: FragmentComponent? = null

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }

    fun getAppComponent() = appComponent

    fun plusFragmentComponent():FragmentComponent{
        return appComponent.plusFragmentComponent()
    }

    fun cleanFragmentComponent(){
        fragmentComponent = null
    }
}
