package com.team2.data.network

import com.team2.data.datasource.PreferenceManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
    private val preferenceManager: PreferenceManager
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response  {
        val request = chain.request().newBuilder().apply {
            runBlocking {
                header("X-CORE-AUTH-TOKEN", "d0ed2d06-1e5f-4b6e-bdb5-e5c6d4692c8f")
//                preferenceManager.userToken.first()?.let {
//                    header("X-CORE-AUTH-TOKEN", "d0ed2d06-1e5f-4b6e-bdb5-e5c6d4692c8f")
//                }
            }
        }.build()
        return chain.proceed(request)
    }
}