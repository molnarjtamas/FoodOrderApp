package com.example.foodorderapp.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderapp.R
import com.example.foodorderapp.home.data.restaurant.Restaurant
import com.example.foodorderapp.home.data.restaurant.RestaurantAdapter


class ListFragment : Fragment() {



    lateinit var restaurants: ArrayList<Restaurant>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvRestaurants = view.findViewById<View>(R.id.rvRestaurants) as RecyclerView
        restaurants = Restaurant.createRestaurantsList(20)

        val adapter = RestaurantAdapter(restaurants)

        rvRestaurants.adapter = adapter

        rvRestaurants.layoutManager = LinearLayoutManager(activity)



    }

}