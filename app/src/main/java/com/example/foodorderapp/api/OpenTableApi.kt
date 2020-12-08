package com.example.foodorderapp

import com.example.foodorderapp.api.OpenTableResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantApi {

    companion object{
        const val BASE_URL = "https://opentable.herokuapp.com/api/"
    }

    @GET("restaurants")
    suspend fun fetchRestaurants(
        @Query("city") city: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): OpenTableResponse
}