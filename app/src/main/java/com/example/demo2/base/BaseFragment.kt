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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(requireActivity()).get(getViewModel().javaClass) as VM
        initView(view, savedInstanceState)
        initObserve()
    }

    protected fun onClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    protected fun navigateTo(@IdRes actionId: Int) {
        NavHostFragment.findNavController(this).navigate(actionId)
    }

    protected fun navigateTo(@IdRes actionId: Int, bundle: Bundle?) {
        NavHostFragment.findNavController(this).navigate(actionId, bundle)
    }

    protected fun popBackStack() {
        NavHostFragment.findNavController(this).popBackStack()
    }

    protected val currentTime: String
        protected get() {
            val simpleDateFormat = SimpleDateFormat("HH:mm")
            return simpleDateFormat.format(System.currentTimeMillis())
        }

    protected fun shortSnackBar(text: String?) {
        Snackbar.make(view!!, text!!, Snackbar.LENGTH_SHORT).show()
    }

    protected fun longSnackBar(text: String?) {
        Snackbar.make(view!!, text!!, Snackbar.LENGTH_LONG).show()
    }

    private var onclickDialog: Callback? = null
    fun showSimpleDialog(message: String?) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(message)
        builder.setNegativeButton("Không") { dialog, which -> dialog.dismiss() }
        builder.setPositiveButton("Có") { dialog, which -> onclickDialog!!.onClick() }
        val dialog = builder.create()
        dialog.show()
    }

    fun setOnclickDialog(onclickDialog: Callback?) {
        this.onclickDialog = onclickDialog
    }

    interface Callback {
        fun onClick()
    }

    override fun onClick(v: View) {}
}