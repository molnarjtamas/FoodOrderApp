package com.example.foodorderapp.home.data.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodorderapp.network.ApiClient
import com.example.foodorderapp.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantRepository {
    private var apiInterface: ApiInterface?=null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAllRestaurants(): LiveData<List<RestaurantModel>> {
        val data = MutableLiveData<List<RestaurantModel>>()

        apiInterface?.fetchAllRestaurants()?.enqueue(object : Callback<List<RestaurantModel>> {

            override fun onFailure(call: Call<List<RestaurantModel>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<List<RestaurantModel>>,
                response: Response<List<RestaurantModel>>
            ) {

                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }

            }
        })

        return data

    }
}