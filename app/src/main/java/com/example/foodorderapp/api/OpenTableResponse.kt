package com.example.foodorderapp.api

import com.example.foodorderapp.data.Restaurant

//This is an envelope object ,we get the restaurants out of the response
data class OpenTableResponse(

    val restaurants: List<Restaurant>
) {
}