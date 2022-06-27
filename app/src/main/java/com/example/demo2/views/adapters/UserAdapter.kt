package com.example.demo2.views.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo2.databinding.UserItemBinding
import com.example.demo2.models.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList: MutableList<User>? = null


    fun setUserList(userList: MutableList<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var userItem = userList?.get(position)
//        holder.binding.name.text = userItem?.name ?: "";
//        holder.binding.gender.text = userItem?.gender ?: "";
        holder.binding.userModel = userItem

        Glide.with(holder.itemView.context).load(userItem?.image).into(holder.binding.image);

    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    inner class UserViewHolder(var binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}