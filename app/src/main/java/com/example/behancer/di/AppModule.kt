package com.example.behancer.di

import androidx.room.Room
import com.example.behancer.AppDelegate
import com.example.behancer.data.Storage
import com.example.behancer.data.database.BehanceDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val appDelegate: AppDelegate) {

    @Provides
    @Singleton
    fun provideApp() = appDelegate

    @Provides
    @Singleton
    fun provideStorage(): Storage {
        val database = Room.databaseBuilder(
            appDelegate,
            BehanceDatabase::class.java,
            "behance_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        return Storage(database.behanceDao)

    }
}