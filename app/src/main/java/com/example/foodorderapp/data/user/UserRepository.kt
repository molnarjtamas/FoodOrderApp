package com.example.foodorderapp.data.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserRepository(private val userDao: UserDao) {

    val readAllUsers: LiveData<List<User>> = userDao.readAllUsers()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    fun getCount():LiveData<Int>{
        return userDao.getCount()
    }

}