package com.example.foodorderapp.data

import androidx.paging.PagingSource
import com.example.foodorderapp.api.OpenTableApi
import com.example.foodorderapp.data.Restaurant
import retrofit2.HttpException
import java.io.IOException

private const val RESTAURANT_STARTING_PAGE_INDEX = 1

class OpenTablePagingSource(
    private val openTableApi: OpenTableApi,
    private val city: String
) : PagingSource<Int, Restaurant>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Restaurant> {
        val position = params.key ?: RESTAURANT_STARTING_PAGE_INDEX
        return try {
            val response = openTableApi.fetchRestaurants(city, position, params.loadSize)
            val restaurants = response.restaurants

            LoadResult.Page(
                data = restaurants,
                prevKey = if (position == RESTAURANT_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (restaurants.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}