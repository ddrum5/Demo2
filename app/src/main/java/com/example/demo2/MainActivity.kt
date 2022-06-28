package com.example.demo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.demo2.base.BaseViewModel
import com.example.demo2.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main )
        val navController = findNavController(this, R.id.nav_host)
        setupWithNavController(binding.bottomNavigation, navController)
        initViewModel()
    }

    private fun initViewModel(){
        baseViewModel = ViewModelProvider(this).get(BaseViewModel::class.java).also {
            it.init(this)
        }

    }


}