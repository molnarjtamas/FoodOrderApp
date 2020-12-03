package com.example.foodorderapp.home.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodorderapp.R
import com.example.foodorderapp.home.data.restaurant.RestaurantAdapter

import com.example.foodorderapp.home.data.restaurant.RestaurantViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var vm:RestaurantViewModel
    private lateinit var adapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }


}
