package com.example.foodorderapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodorderapp.data.favorite.Favorite
import com.example.foodorderapp.data.favorite.FavoriteDao
import com.example.foodorderapp.data.user.User
import com.example.foodorderapp.data.user.UserDao

@Database(entities = [User::class, Favorite::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun favoriteDao(): FavoriteDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase?= null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance= INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}