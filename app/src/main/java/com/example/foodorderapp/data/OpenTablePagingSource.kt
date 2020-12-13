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
        //trying to load the data
        return try {
            //using the interface to fetch the data
            val response = openTableApi.fetchRestaurants(city)
            val restaurants = response.restaurants

            //using paging to load the data
            LoadResult.Page(
                data = restaurants,
                //if we are on the page 1 we don't have previous page
                prevKey = if (position == RESTAURANT_STARTING_PAGE_INDEX) null else position - 1,
                //if we have no more entries there is no next page
                nextKey = if (restaurants.isEmpty()) null else position + 1
            )

        } catch (exception: IOException) {
            //input-output error
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            //server or request error
            LoadResult.Error(exception)
        }
    }
}