package com.example.behancer.di

import com.example.behancer.AppDelegate
import com.example.behancer.data.Storage
import com.example.behancer.di.provider.StorageProvider
import toothpick.config.Module
import javax.inject.Inject


class AppModule @Inject constructor(appDelegate: AppDelegate) : Module() {

    init {
        bind(AppDelegate::class.java).toInstance(appDelegate)
        bind(Storage::class.java).toProvider(StorageProvider::class.java).singleton()
    }
}