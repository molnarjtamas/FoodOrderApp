package com.example.foodorderapp.ui.restaurants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderapp.databinding.RestaurantLoadStateFooterBinding

class RestaurantLoadStateAdapter (private val retry: () -> Unit):
    LoadStateAdapter<RestaurantLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = RestaurantLoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: RestaurantLoadStateFooterBinding):
        RecyclerView.ViewHolder(binding.root){

        init {
            binding.buttonRetry.setOnClickListener{
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState){
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
                textViewError.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}