package com.example.behancer.di.provider

import com.example.behancer.data.api.BehanceApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class BehanceApiProvider : Provider<BehanceApi> {

    @Inject
    lateinit var retrofit: Retrofit

    override fun get(): BehanceApi {
        return retrofit.create<BehanceApi>(BehanceApi::class.java)
    }
}