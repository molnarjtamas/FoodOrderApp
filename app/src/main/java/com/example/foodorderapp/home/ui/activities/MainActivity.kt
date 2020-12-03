package com.example.foodorderapp.home.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodorderapp.R
import com.example.foodorderapp.home.data.restaurant.RestaurantAdapter

import com.example.foodorderapp.home.data.restaurant.RestaurantViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpNavigation()


    }

    fun setUpNavigation() {
        bottomNavigationView= findViewById(R.id.btm_nav)
        val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(
                bottomNavigationView,
                navHostFragment!!.navController
        )
    }


}
