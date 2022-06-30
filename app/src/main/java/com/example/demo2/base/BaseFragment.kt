package com.example.demo2.base


import com.example.demo2.base.BaseViewModel
import androidx.databinding.ViewDataBinding
import android.view.View
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.IdRes
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
abstract class BaseFragment<VM : BaseViewModel?, B : ViewDataBinding?> : Fragment(),
    View.OnClickListener {
    protected abstract val layout: Int
    internal abstract fun getViewModel(): VM
    protected abstract fun initView(view: View, savedInstanceState: Bundle?)
    protected fun initObserve() {}
    protected var binding: B? = null
    protected var viewModel: VM? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        return binding!!.root
    }



}