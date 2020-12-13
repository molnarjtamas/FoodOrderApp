package com.example.foodorderapp.ui.restaurants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.foodorderapp.R
import com.example.foodorderapp.data.Restaurant

import com.example.foodorderapp.databinding.ItemRestaurantBinding

class RestaurantAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<Restaurant, RestaurantAdapter.RestaurantViewHolder>(
    RESTAURANT_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }

    }

    inner class RestaurantViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener{
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item != null){
                        listener.onItemClick(item)
                    }
                }

            }
        }

        fun bind(restaurant: Restaurant) {

            binding.apply {
                textViewRestaurantName.text = restaurant.name
                textViewAddress.text = restaurant.address
                textViewPrice.text = restaurant.price.toString()
                Glide.with(itemView)
                    .load(restaurant.image_url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(restaurant: Restaurant)
    }
    companion object {
        private val RESTAURANT_COMPARATOR = object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) =
                oldItem == newItem
        }
    }
}