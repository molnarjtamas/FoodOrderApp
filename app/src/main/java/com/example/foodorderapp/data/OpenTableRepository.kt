package com.example.foodorderapp


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.foodorderapp.api.OpenTableApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepository @Inject constructor (private val openTableApi: OpenTableApi) {

    fun getSearchResult(city: String) =
        Pager(
            config = PagingConfig(
                pageSize = 25,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RestaurantPagingSource(openTableApi, city) }
        ).liveData
}