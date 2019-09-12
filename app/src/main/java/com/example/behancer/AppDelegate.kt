package com.example.behancer

import android.app.Application
import androidx.room.Room
import com.example.behancer.data.Storage
import com.example.behancer.data.database.BehanceDatabase
import com.example.behancer.di.AppComponent
import com.example.behancer.di.AppModule
import com.example.behancer.di.DaggerAppComponent
import com.example.behancer.di.NetworkModule

class AppDelegate : Application() {

    companion object {
        private lateinit var appComponent: AppComponent

        fun getAppComponent() = appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }


}
