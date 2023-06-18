package com.example.github.core.network.core

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object Retrofit {

    inline operator fun <reified T> invoke(
        baseUrl: String,
    ): T = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttp())
        .build().create()

    object OkHttp {
        operator fun invoke(
            interceptor: Interceptor? = null,
            authenticator: Authenticator? = null,
        ): OkHttpClient = OkHttpClient().newBuilder().apply {
            interceptor?.let { addInterceptor(it) }
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            connectTimeout(timeout = 30, TimeUnit.SECONDS)
            readTimeout(timeout = 30, TimeUnit.SECONDS)
            writeTimeout(timeout = 30, TimeUnit.SECONDS)
            authenticator(authenticator = authenticator ?: Authenticator.NONE)
        }.build()
    }

}