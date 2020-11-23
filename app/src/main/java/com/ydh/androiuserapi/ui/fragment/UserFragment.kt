package com.ydh.androiuserapi.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ydh.androiuserapi.R
import com.ydh.androiuserapi.databinding.FragmentUserBinding
import com.ydh.androiuserapi.model.user.UserModel
import com.ydh.androiuserapi.ui.adapter.UserMainAdapter
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
        setListener()
        setObserver()
        return binding.root
    }

    private fun setViewModel(){
        userViewModel = ViewModelProviders.of(this, UserViewModelFactory(this.context)).get(
                UserViewModel::class.java)
    }

    private fun setListener(){
        binding.etUserFilter.addTextChangedListener(filterListener)
    }
    private val filterListener = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            myAdapter?.filter(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {
            myAdapter?.filter(s.toString())
        }
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