package com.example.demo2.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.demo2.R
import com.example.demo2.databinding.FragmentFavoriteBinding
import com.example.demo2.ui.home.adapters.UserAdapter

class FavoriteFragment : Fragment() {


    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteViewModel = FavoriteViewModel(requireContext())

        lifecycleScope.launchWhenCreated {
            favoriteViewModel.init()
        }

//        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        initDataRecyclerView()

    }

    private fun initDataRecyclerView() {
        userAdapter = UserAdapter()
        binding.rcvListUser.adapter = userAdapter

        favoriteViewModel.users.observe(viewLifecycleOwner, Observer {
            userAdapter.setUserList(it)
        })

//        favoriteViewModel.getUsers().observe(viewLifecycleOwner, Observer {
//            userAdapter.setUserList(it)
//        })



    }


}