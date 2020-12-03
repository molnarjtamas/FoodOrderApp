package com.example.foodorderapp.network

import com.example.foodorderapp.home.data.restaurant.RestaurantModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("restaurants")
    fun fetchAllRestaurants(): Call<List<RestaurantModel>>
}