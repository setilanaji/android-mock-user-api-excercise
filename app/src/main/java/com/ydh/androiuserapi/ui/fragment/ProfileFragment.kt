package com.ydh.androiuserapi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ydh.androiuserapi.R
import com.ydh.androiuserapi.databinding.FragmentProfileBinding
import com.ydh.androiuserapi.viewmodel.ProfileViewModel
import com.ydh.androiuserapi.viewmodel.RegisterViewModel
import com.ydh.androiuserapi.viewmodel.factory.ProfileViewModelFactory
import com.ydh.androiuserapi.viewmodel.factory.RegisterViewModelFactory


class ProfileFragment : Fragment() {


    lateinit var binding: FragmentProfileBinding
    lateinit var profileViewModel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        setViewModel()
        binding.run {
            btProfileLogout.setOnClickListener{
                profileViewModel.logOut()
                findNavController().navigate(R.id.onboardingFragment)
            }
        }

        return binding.root
    }

    private fun setViewModel(){
        profileViewModel =   ViewModelProviders.of(this, ProfileViewModelFactory(this.context)).get(
                ProfileViewModel::class.java)
    }






}