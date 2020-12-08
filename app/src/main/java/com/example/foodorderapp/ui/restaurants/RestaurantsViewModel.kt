package com.example.foodorderapp.ui.restaurants


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.foodorderapp.data.OpenTableRepository


class RestaurantsViewModel @ViewModelInject constructor(
    private val repository: OpenTableRepository
    ): ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)
    val restaurants = currentQuery.switchMap { cityString ->
        repository.getSearchResult(cityString).cachedIn(viewModelScope)
    }

    fun searchRestaurants(city: String){
        currentQuery.value = city
    }
    companion object {
        private const val DEFAULT_QUERY = "Chicago"
    }
}