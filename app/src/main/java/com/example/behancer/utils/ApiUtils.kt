package com.example.behancer.utils

import com.example.behancer.BuildConfig
import com.example.behancer.data.api.ApiKeyInterceptor
import com.example.behancer.data.api.BehanceApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

object ApiUtils {

    val NETWORK_EXCEPTIONS: List<Class<*>> = Arrays.asList<Class<out IOException>>(
        UnknownHostException::class.java,
        SocketTimeoutException::class.java,
        ConnectException::class.java
    )
}