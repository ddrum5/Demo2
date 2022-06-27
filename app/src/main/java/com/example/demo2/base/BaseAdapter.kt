package com.example.demo2.base

import android.content.Context
import com.example.demo2.base.BaseViewHolder
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater

abstract class BaseAdapter<M, VH : BaseViewHolder<B>?, B : ViewDataBinding?>(var context: Context) :
    RecyclerView.Adapter<VH>() {
    protected var list: MutableList<M>? = null
    protected abstract val layout: Int
    protected abstract fun getViewHolder(binding: B): VH
    protected abstract fun bindView(item: M, holder: VH, position: Int)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(parent.context),
            layout, parent, false
        )
        return getViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        bindView(list!![position], holder, position)
    }

    override fun getItemCount(): Int {
        return if (list != null) {
            list!!.size
        } else 0
    }

    fun updateData(list: MutableList<M>?) {
        this.list = list
        notifyDataSetChanged()
    }

    fun clearData() {
        list!!.clear()
        notifyDataSetChanged()
    }
}