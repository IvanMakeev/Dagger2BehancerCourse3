package com.example.behancer.di.provider

import com.example.behancer.BuildConfig
import com.example.behancer.data.api.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor(): Provider<OkHttpClient> {

    override fun get(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        builder.addInterceptor(ApiKeyInterceptor())
        if (!BuildConfig.BUILD_TYPE.contains("release")) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return builder.build()
    }
}