package com.team2.data.network

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response  {
        val request = chain.request().newBuilder().apply {
            runBlocking {
                header("X-CORE-AUTH-TOKEN", "123123")
            }
        }.build()
        return chain.proceed(request)
    }
}