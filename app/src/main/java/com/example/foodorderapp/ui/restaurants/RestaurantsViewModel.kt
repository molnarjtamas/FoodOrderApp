package com.example.foodorderapp.ui.restaurants


import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.foodorderapp.data.OpenTableRepository


class RestaurantsViewModel @ViewModelInject constructor(
    private val repository: OpenTableRepository,
    @Assisted state: SavedStateHandle
    ): ViewModel() {

    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)
    val restaurants = currentQuery.switchMap { cityString ->
        repository.getSearchResult(cityString).cachedIn(viewModelScope)
    }

    fun searchRestaurants(city: String){
        currentQuery.value = city
    }
    companion object {
        private const val CURRENT_QUERY="current_query"
        private const val DEFAULT_QUERY = "Dallas"
    }
}