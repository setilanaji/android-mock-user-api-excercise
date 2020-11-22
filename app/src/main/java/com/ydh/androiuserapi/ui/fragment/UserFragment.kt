package com.ydh.androiuserapi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.ydh.androiuserapi.R
import com.ydh.androiuserapi.databinding.FragmentUserBinding
import com.ydh.androiuserapi.model.UserModel
import com.ydh.androiuserapi.ui.UserAdapter
import com.ydh.androiuserapi.ui.UserMainAdapter
import com.ydh.androiuserapi.viewmodel.UserViewModel
import com.ydh.androiuserapi.viewmodel.factory.UserViewModelFactory


class UserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    lateinit var binding: FragmentUserBinding
    private val myAdapter by lazy {
        context?.let { UserMainAdapter(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)

        setViewModel()
        setData()
        setView()
        setObserver()
        return binding.root
    }

    private fun setViewModel(){
        userViewModel = ViewModelProviders.of(this, UserViewModelFactory(this.context)).get(
                UserViewModel::class.java)
    }

    private fun setView(){
        binding.rvUserFiltered.adapter = myAdapter
    }

    private fun setData(){
        userViewModel.setAllUser()
    }

    private fun setObserver(){
        userViewModel.users.observe(viewLifecycleOwner, {
            myAdapter?.setData(it as MutableList<UserModel>)
        })
    }

}