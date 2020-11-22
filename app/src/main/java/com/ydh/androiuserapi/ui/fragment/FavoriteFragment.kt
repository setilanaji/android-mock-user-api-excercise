package com.ydh.androiuserapi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.ydh.androiuserapi.R
import com.ydh.androiuserapi.databinding.FragmentFavoriteBinding
import com.ydh.androiuserapi.model.User
import com.ydh.androiuserapi.model.user.UserModel
import com.ydh.androiuserapi.ui.adapter.UserAdapter
import com.ydh.androiuserapi.viewmodel.FavoriteViewModel
import com.ydh.androiuserapi.viewmodel.factory.FavoriteViewModelFactory


class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    private val favoriteAdapter by lazy {
        context?.let { UserAdapter(it) }
    }
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)

        setViewModel()
        setData()
        setView()
        setObserver()
        return binding.root
    }

    private fun setView(){
        binding.rvFavUser.adapter = favoriteAdapter
    }

    private fun setViewModel(){
        favoriteViewModel = ViewModelProviders.of(this, FavoriteViewModelFactory(this.context))
                .get(FavoriteViewModel::class.java)
    }

    private fun setData(){
        favoriteViewModel.setAllUser()
    }

    private fun setObserver(){

        favoriteViewModel.users.observe(viewLifecycleOwner, {
            generateProduct(it)
        })
    }

    private fun generateProduct(it: List<UserModel>) {
        val list = mutableListOf<User>()
        val sortedList = it.sortedBy { it.name.first }
        var temp = ""

        sortedList.forEach { model ->
            if (temp != model.name.first[0].toUpperCase().toString()) {
                temp = model.name.first[0].toUpperCase().toString()
                list.add(User.Category(temp))
            }
            list.add(User.Data(model))
        }
        favoriteAdapter?.setData(list)
    }

}