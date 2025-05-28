package com.example.gpt.di.modules

import android.R.attr.apiKey
import com.example.gpt.BuildConfig
import com.example.gpt.data.AiService
import com.example.gpt.data.network.models.ApiKey
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import okhttp3.Request
import okhttp3.Response


@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun okHttpClient(apiKey: String): OkHttpClient = OkHttpClient.Builder()
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

    @Provides
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.openai.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAiService(retrofit: Retrofit): AiService {
        return retrofit.create(AiService::class.java)
    }
    
}