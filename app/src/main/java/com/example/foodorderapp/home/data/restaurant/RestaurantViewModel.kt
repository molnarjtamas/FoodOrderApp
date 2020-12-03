package com.example.foodorderapp.home.data.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RestaurantViewModel {

    private var restaurantRepository:RestaurantRepository?=null
    var restaurantModelListViewData: LiveData<List<RestaurantModel>>?=null
    init {
        restaurantRepository = RestaurantRepository()
        restaurantModelListViewData = MutableLiveData()
    }

    fun fetchAllRestaurants(){
        restaurantModelListViewData = restaurantRepository?.fetchAllRestaurants()
    }
}