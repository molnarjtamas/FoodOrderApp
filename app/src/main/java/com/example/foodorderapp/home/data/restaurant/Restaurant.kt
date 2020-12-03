package com.example.foodorderapp.home.data.restaurant

class Restaurant(val restaurantName:String , val address: String, val price: Int) {


    companion object {
        private var number = 0
        fun createRestaurantsList(numRestaurants: Int): ArrayList<Restaurant> {
            val restaurants = ArrayList<Restaurant>()
            for (i in 1..numRestaurants) {
                restaurants.add(
                    Restaurant(
                        "ExampleRestaurant " + ++number,
                        "ExampleAddress" + ++number,
                        ++number
                    )
                )
            }
            return restaurants
        }
    }
}