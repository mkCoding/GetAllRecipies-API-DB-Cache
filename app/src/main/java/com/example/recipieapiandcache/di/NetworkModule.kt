package com.example.recipieapiandcache.di

import com.example.recipieapiandcache.data.api.ApiDetails
import com.example.recipieapiandcache.data.api.RecipieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit =
    Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesRecipieAPI(retrofit: Retrofit): RecipieApi{
        return retrofit.create(RecipieApi::class.java)
    }


}