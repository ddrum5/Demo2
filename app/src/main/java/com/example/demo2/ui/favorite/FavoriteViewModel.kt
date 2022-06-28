package com.example.demo2.ui.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo2.base.BaseViewModel
import com.example.demo2.data.models.User
import com.example.demo2.data.repository.UserRepository
import com.example.demo2.data.roomdatabase.RoomDatabaseInstance
import com.example.demo2.data.roomdatabase.UserDao

class FavoriteViewModel() : BaseViewModel() {


    private val users = MutableLiveData<MutableList<User>>()

     fun initData() {
       userDao.getAll()?.also {
           users.value = it
       }
    }
    fun getUsers(): LiveData<MutableList<User>> {
        return users
    }




}