package com.example.demo2.base

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo2.data.models.User
import com.example.demo2.data.repository.UserRepository
import com.example.demo2.data.roomdatabase.RoomDatabaseInstance
import com.example.demo2.data.roomdatabase.UserDao

open class BaseViewModel() : ViewModel() {

    protected lateinit var context: Context
    protected lateinit var userDao: UserDao

    fun init(context: Context) {
        this.context = context
        this.userDao = RoomDatabaseInstance.getInstance(context)!!.userDao()
    }


}