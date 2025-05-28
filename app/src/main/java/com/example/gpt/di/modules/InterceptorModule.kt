package com.example.gpt.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {
    @Provides
    fun okHttpClient(@ApiKeyQualifier apiKey: String): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request = chain.request()
                        .newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer $apiKey")
                        .build()
                    return chain.proceed(request)
                }
            }
        )
        .build()
}

