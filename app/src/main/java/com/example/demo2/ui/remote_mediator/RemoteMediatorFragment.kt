package com.example.demo2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.example.demo2.R
import com.example.demo2.databinding.FragmentRemoteMediatorBinding
import com.example.demo2.ui.home.adapters.UserAdapterPaging
import com.example.demo2.ui.remote_mediator.RemoteMediatorViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalPagingApi::class)
class RemoteMediatorFragment : Fragment() {

    private lateinit var binding: FragmentRemoteMediatorBinding
    private lateinit var viewModel: RemoteMediatorViewModel
    private lateinit var adapter: UserAdapterPaging

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_remote_mediator, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = RemoteMediatorViewModel(requireContext())
//        viewModel = ViewModelProvider(this).get(RemoteMediatorViewModel::class.java)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        adapter = UserAdapterPaging(retry = {
            view?.let { item -> Snackbar.make(item, it.name.toString() , Snackbar.LENGTH_SHORT).show() }
        })
        binding.rcvListUser.adapter = adapter

    }


    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.loadData().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}
