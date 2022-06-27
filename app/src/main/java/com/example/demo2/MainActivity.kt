package com.example.demo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.demo2.databinding.ActivityMainBinding
import com.example.demo2.databinding.FragmentHomeBinding
import com.example.demo2.viewmodels.HomeViewModel
import com.example.demo2.views.adpter.UserAdapter

class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: HomeViewModel
    private lateinit var userAdapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main )

        val navController = findNavController(this, R.id.nav_host)
        setupWithNavController(binding.bottomNavigation, navController)

    }






}