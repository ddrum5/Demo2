package com.example.demo2.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B : ViewDataBinding?>(var binding: B) : RecyclerView.ViewHolder(
    binding!!.root
)