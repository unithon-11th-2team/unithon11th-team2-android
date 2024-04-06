package com.team2.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.team2.data.datasource.PreferenceManager
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
object ApiProvider {
    const val BASE_URL = "http://15.165.229.122:8080/"
    val contentType = "application/json".toMediaType()

    val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val jsonConverter = Json.asConverterFactory("application/json".toMediaType())


    inline fun <reified T> create(preferenceManager: PreferenceManager): T{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConverter)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(RequestInterceptor(preferenceManager))
                    .addInterceptor(logger)
                    .connectTimeout(1000, TimeUnit.SECONDS)
                    .writeTimeout(1000, TimeUnit.SECONDS)
                    .readTimeout(1000, TimeUnit.SECONDS)
                    .build()
            )
            .build()
            .create(T::class.java)
    }

    inline fun <reified T> createWithoutRequestToken(): T{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConverter)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .connectTimeout(1000, TimeUnit.SECONDS)
                    .readTimeout(1000, TimeUnit.SECONDS)
                    .writeTimeout(1000, TimeUnit.SECONDS)
                    .build()
            )
            .build()
            .create(T::class.java)
    }
}