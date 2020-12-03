package com.example.foodorderapp.home.data.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderapp.R
import com.example.foodorderapp.home.data.restaurant.RestaurantModel


class RestaurantAdapter(var listener: RestaurantListener): RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>(){

    private var data : ArrayList<RestaurantModel>?=null

    interface RestaurantListener{
        fun onItemDeleted(restaurantModel: RestaurantModel,position: Int)
    }

    fun setData(list: ArrayList<RestaurantModel>){
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.restaurant_rv_item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
    }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(item: RestaurantModel?) {
//            itemView.restaurantName.text = item?.name
//            itemView.address.text = item?.address
        }

    }
}