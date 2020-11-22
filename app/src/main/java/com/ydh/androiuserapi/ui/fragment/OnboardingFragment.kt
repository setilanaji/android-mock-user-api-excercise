package com.ydh.androiuserapi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.ydh.androiuserapi.R
import com.ydh.androiuserapi.databinding.FragmentOnboardingBinding


class OnboardingFragment : Fragment() {
    lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding, container, false)



        setListener()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {}
        callback.isEnabled = false
    }

    private fun setListener(){
        binding.btToLogin.setOnClickListener(buttonLoginListener)
        binding.btToRegister.setOnClickListener(buttonToRegisterListener)
    }

    private val buttonLoginListener = View.OnClickListener {
        it.findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
    }

    private val buttonToRegisterListener = View.OnClickListener {
        it.findNavController().navigate(R.id.action_onboardingFragment_to_registerFragment)
    }

}