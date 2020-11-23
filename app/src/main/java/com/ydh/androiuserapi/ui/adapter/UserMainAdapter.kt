package com.ydh.androiuserapi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ydh.androiuserapi.R
import com.ydh.androiuserapi.databinding.ItemUserMainBinding
import com.ydh.androiuserapi.model.User
import com.ydh.androiuserapi.model.user.UserModel

class UserMainAdapter(
        private val context: Context,
) : RecyclerView.Adapter<UserMainAdapter.MyViewHolder>(), Filterable {
    private var userList = mutableListOf<UserModel>()
    private val filteredPosts: MutableList<UserModel> = mutableListOf()
    private var oldFilteredPosts: MutableList<UserModel> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemUserMainBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_user_main, parent, false)
        return MyViewHolder(binding)
    }

    fun setData(item: MutableList<UserModel>) {
        this.userList = item
        notifyDataSetChanged()
    }

    //    fun filterData(text: String){
//        if (text.isEmpty()){
//            userList = userListTemp
//        }else{
//            userListTemp = userList
//           val filteredList = userList.filter { it.name.first.contains(text) }
//            this.userList = filteredList as MutableList<UserModel>
//        }
//        notifyDataSetChanged()
//    }
    class PostsDiffUtilCallback(private val oldList: List<UserModel>, private val newList: List<UserModel>) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.user = userList[position]
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class MyViewHolder(val itemBinding: ItemUserMainBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        private var binding: ItemUserMainBinding? = null

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

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

}