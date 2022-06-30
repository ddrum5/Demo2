package com.example.demo2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.demo2.R
import com.example.demo2.data.models.User
import com.example.demo2.databinding.FragmentHomeBinding
import com.example.demo2.ui.home.adapters.UserAdapter
import com.example.demo2.ui.home.adapters.UserAdapterPaging
import com.example.demo2.ui.home.adapters.UserListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class HomeFragment : Fragment() {

//
//    private lateinit var binding: FragmentHomeBinding
//    private lateinit var homeViewModel: HomeViewModel
//    private lateinit var userAdapter: UserAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//        context?.let { homeViewModel.init(it) };
//        lifecycleScope.launch {
//              homeViewModel.initData()
//        }
//        initRecyclerView()
//    }
//
//    private fun initRecyclerView() {
//        userAdapter = UserAdapter()
//        binding.rcvListUser.adapter = userAdapter
//
//        homeViewModel.getUsers().observe(viewLifecycleOwner, Observer {
//            userAdapter.setUserList(it)
//        })
//
//        userAdapter.setClick(object : UserClick {
//            override fun onClick(user: User) {
//                view?.let { Snackbar.make(it, "Da luu", Snackbar.LENGTH_SHORT).show() }
//                homeViewModel.saveUserToDB(user)
//            }
//        })
//
//    }
//
//}

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var userAdapter: UserAdapterPaging

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
        homeViewModel = HomeViewModel(requireContext())
//        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        userAdapter = UserAdapterPaging(retry = {
            view?.let { item -> Snackbar.make(item, it.name.toString() , Snackbar.LENGTH_SHORT).show() }
        })
        binding.rcvListUser.adapter = userAdapter




    }


    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            homeViewModel.loadData().collectLatest {
                userAdapter.submitData(it)
            }
        }
    }
}
