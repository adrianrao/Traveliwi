package dev.adrianrao.traveliwi.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.adrianrao.traveliwi.home.data.remote.ApiKeyInterceptor
import dev.adrianrao.traveliwi.home.data.remote.HomeRepositoryImpl
import dev.adrianrao.traveliwi.home.data.remote.OpenAIApi
import dev.adrianrao.traveliwi.home.domain.repository.HomeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HomeModule {
    @Provides
    @Singleton
    fun provideApi(): OpenAIApi {
        return Retrofit.Builder().baseUrl(OpenAIApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).addInterceptor(ApiKeyInterceptor()).build())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRepository(api: OpenAIApi): HomeRepository = HomeRepositoryImpl(api)
}