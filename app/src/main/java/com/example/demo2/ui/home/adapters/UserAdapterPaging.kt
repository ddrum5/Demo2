package com.example.demo2.ui.home.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demo2.data.models.User
import com.example.demo2.databinding.UserItemBinding

class UserAdapterPaging(
    private val retry: (user: User)-> Unit
) : PagingDataAdapter<User, UserAdapterPaging.UserViewHolder>(DIFF_CALLBACK) {

    var userListener: UserListener? = null
    var usersFavorite : MutableList<User> ? = null



    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.name == newItem.name
                        && oldItem.gender == newItem.gender
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.run {
            holder.binding.userModel = this

            holder.binding.btnSave.setOnClickListener {
                userListener?.onClick(this)
                retry(this)
            }
            if(usersFavorite?.get(position)?.id == this.id) {
                holder.binding.btnSave.visibility = View.INVISIBLE
            } else {
                holder.binding.btnSave.visibility = View.VISIBLE
            }


        }
    }

    class UserViewHolder(var binding: UserItemBinding, retry: (user: User) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
    }


}

