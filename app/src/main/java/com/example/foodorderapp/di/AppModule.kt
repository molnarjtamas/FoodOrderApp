package com.example.foodorderapp.di

import com.example.foodorderapp.api.OpenTableApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


//using dagger hilt for dependency injection
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

//making retrofit connection Singleton ,so we only make one instance of the connection
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit=
        Retrofit.Builder()
            .baseUrl(OpenTableApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): OpenTableApi =
        retrofit.create(OpenTableApi::class.java)

}