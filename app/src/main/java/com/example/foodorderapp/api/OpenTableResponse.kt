package com.example.foodorderapp.api

import com.example.foodorderapp.data.Restaurant

data class OpenTableResponse(
    val restaurants: List<Restaurant>
) {
}