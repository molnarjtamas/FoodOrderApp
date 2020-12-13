package com.example.foodorderapp.data.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class Favorite(
    @PrimaryKey
    val id:Int,
    val name:String,
    val address:String,
    val city:String,
    val state:String,
    val area:String,
    val postal_code:String,
    val country:String,
    val phone:String,
    val lat:Double,
    val lng:Double,
    val price:Int,
    val reserve_url:String,
    val mobile_reserve_url:String,
    val image_url:String,
    val user_id: Int
)
