package com.example.behancer.di.provider

import androidx.room.Room
import com.example.behancer.AppDelegate
import com.example.behancer.data.Storage
import com.example.behancer.data.database.BehanceDatabase
import javax.inject.Inject
import javax.inject.Provider

class StorageProvider : Provider<Storage> {

    @Inject
    lateinit var appDelegate: AppDelegate

    override fun get(): Storage {
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