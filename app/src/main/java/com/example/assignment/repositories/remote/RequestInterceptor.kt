package com.example.assignment.repositories.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val urlHttp = request.url
        val updatedUrl = urlHttp.newBuilder()
            .addQueryParameter("api-key", apiKey)
            .build()
        val finalRequest = request.newBuilder().url(updatedUrl).build()
        return chain.proceed(finalRequest)
    }
}