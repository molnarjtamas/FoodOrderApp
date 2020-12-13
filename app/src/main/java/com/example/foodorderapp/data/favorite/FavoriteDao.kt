package com.example.foodorderapp.data.favorite

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: Favorite)

    @Update
    fun updateFavorite(favorite: Favorite)

    @Delete
    fun deleteFavorite(favorite: Favorite)


    @Query("SELECT * FROM favorite_table")
    fun getFavorites(): LiveData<List<Favorite>>
}
