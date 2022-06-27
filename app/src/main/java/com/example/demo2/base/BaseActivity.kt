package com.example.demo2.base

import com.example.demo2.base.BaseViewModel
import androidx.databinding.ViewDataBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {
    protected abstract val layout: Int

    internal abstract fun getViewModel(): VM
    protected abstract fun initView(savedInstanceState: Bundle?)
    protected fun initObserve() {}
    protected var binding: B? = null
    protected  var viewModel: VM? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout)
        viewModel = ViewModelProvider(this).get(getViewModel().javaClass) as VM
        initView(savedInstanceState)
        initObserve()
    }
}