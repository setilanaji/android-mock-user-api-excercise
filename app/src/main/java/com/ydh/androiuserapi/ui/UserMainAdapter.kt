package com.ydh.androiuserapi.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ydh.androiuserapi.R
import com.ydh.androiuserapi.databinding.ItemUserMainBinding
import com.ydh.androiuserapi.model.UserModel

class UserMainAdapter(
        private val context: Context,
) : RecyclerView.Adapter<UserMainAdapter.MyViewHolder>() {
    private var userList = mutableListOf<UserModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserMainAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemUserMainBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_user_main,parent,false)
        return MyViewHolder(binding)
    }

    fun setData(item: MutableList<UserModel>){
        this.userList = item
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.user = userList[position]
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(val itemBinding: ItemUserMainBinding) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : ItemUserMainBinding? = null

        init {
            this.binding = itemBinding
        }

        companion object {

            @JvmStatic
            @BindingAdapter("profileImage")
            fun loadImage(view: ImageView, profileImage: String) {
                Glide.with(view.context)
                        .load(profileImage)
                        .circleCrop()
                        .into(view)
            }
        }

    }

}