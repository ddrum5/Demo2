package com.example.demo2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo2.models.User
import com.example.demo2.repository.UserRepository

class HomeViewModel : ViewModel() {

    private val users = MutableLiveData<MutableList<User>>()


    fun getUsers(): LiveData<MutableList<User>> {
        return users
    }

     suspend fun getUserAPI() {
        UserRepository().getData().also {
            users.value = it
        }

    }


//    private var users = MutableLiveData<MutableList<User>>()
//    init {
//        var list = mutableListOf(
//            User("Dinh", "nam"),
//            User("Dinh", "nam"),
//            User("Dinh", "nam"),
//            User("Dinh", "nam"),
//            User("Dinh", "nam")
//        )
//        users.value = list;

//    }
//
    fun addItem(name: String, gender: String) {

        var list = users.value?.apply {
            add(User(name, gender))
        }
        users.value = list;
    }

}