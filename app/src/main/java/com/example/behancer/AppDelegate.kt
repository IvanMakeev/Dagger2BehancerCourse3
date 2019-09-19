package com.example.behancer

import android.app.Application
import com.example.behancer.di.*
import toothpick.MemberInjector
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration

class AppDelegate : Application() {

    companion object {
        private lateinit var appScope: Scope
        @JvmStatic
        fun getAppScope() = appScope
    }

    override fun onCreate() {
        super.onCreate()
        appScope = Toothpick.openScope(AppDelegate::class.java)
        appScope.installModules(AppModule(this), NetworkModule())
    }
}
