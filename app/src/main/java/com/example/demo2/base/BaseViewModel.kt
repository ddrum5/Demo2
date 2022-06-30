package com.example.demo2.base

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.demo2.data.db.UserDao
import com.example.demo2.data.db.UserDatabase
import com.example.demo2.data.repository.UserRepository

open class BaseViewModel(context: Context) : ViewModel() {


    protected  var userDao: UserDao
    protected  var userRepository: UserRepository

     init {
        this.userDao = UserDatabase.getInstance(context).userDao()
        this.userRepository = UserRepository(context.applicationContext)
    }


}