package com.example.demo2.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.demo2.R
import com.example.demo2.databinding.FragmentHomeBinding
import com.example.demo2.viewmodels.HomeViewModel
import com.example.demo2.views.adpter.UserAdapter
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var userViewModel: HomeViewModel
    private lateinit var userAdapter: UserAdapter


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
//    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        lifecycleScope.launch {
            userViewModel.getUserAPI()
        }

        userAdapter = UserAdapter()
        binding.rcvListUser.adapter = userAdapter
        initData()

    }

    private fun initData() {
        userViewModel.getUsers().observe(viewLifecycleOwner, Observer {
            userAdapter.setUserList(it)
        })
    }




}