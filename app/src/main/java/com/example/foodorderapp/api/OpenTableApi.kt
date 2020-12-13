package com.example.foodorderapp.api

import com.example.foodorderapp.api.OpenTableResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTableApi {

    companion object{
        const val BASE_URL = "https://ratpark-api.imok.space/"
    }

    //Get request to the Api
    @GET("restaurants")
    suspend fun fetchRestaurants(
        @Query("city") city: String
    ): OpenTableResponse
}