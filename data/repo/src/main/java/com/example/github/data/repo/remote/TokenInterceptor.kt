package com.example.github.data.repo.remote

import com.example.github.data.repo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {

    @Synchronized
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val token = BuildConfig.TOKEN

        val requestBuilder = request.newBuilder().apply {
            header("accept", "application/json")
            header("Authorization", "token $token")
        }

        request = requestBuilder.build()
        return chain.proceed(request)
    }
}