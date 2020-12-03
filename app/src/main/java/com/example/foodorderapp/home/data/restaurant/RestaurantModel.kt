package com.example.foodorderapp.home.data.restaurant

data class RestaurantModel(
    var id:Int?=0,
    var name:String?="",
    var address:String?="",
    var city:String?="",
    var state:String?="",
    var area:String?="",
    var postal_code:String?="",
    var country:String?="",
    var phone:String?="",
    var lat:Double?=0.000000,
    var lng:Double?=0.000000,
    var price:Int?=0,
    var reserve_url:String?="",
    var mobile_reserve_url:String?="",
    var image_url:String?=""

)
