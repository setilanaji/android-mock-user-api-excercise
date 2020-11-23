package com.ydh.androiuserapi.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.ydh.androiuserapi.R
import com.ydh.androiuserapi.databinding.FragmentLoginBinding
import com.ydh.androiuserapi.databinding.FragmentRegisterBinding
import com.ydh.androiuserapi.viewmodel.LoginViewModel
import com.ydh.androiuserapi.viewmodel.RegisterViewModel
import com.ydh.androiuserapi.viewmodel.factory.LoginViewModelFactory
import com.ydh.androiuserapi.viewmodel.factory.RegisterViewModelFactory


class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    lateinit var registerViewModel: RegisterViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        setViewModel()
        setListener()
        setObserver()
        return binding.root
    }

    private fun setViewModel(){
        registerViewModel =   ViewModelProviders.of(this, RegisterViewModelFactory(this.context)).get(
            RegisterViewModel::class.java)
    }

    private fun setListener(){
        binding.etRegisterUserName.addTextChangedListener(nameListener)
        binding.etRegisterUserEmail.addTextChangedListener(emailListener)
        binding.etRegisterUserPassword.addTextChangedListener(passwordListener)
        binding.btRegLogin.setOnClickListener(buttonLoginListener)
        binding.btRegRegister.setOnClickListener(buttonToRegisterListener)
    }

    private fun setObserver(){
        registerViewModel.apply {
            isEmailValid.observe(viewLifecycleOwner, isValidEmailObserver)
            isPasswordValid.observe(viewLifecycleOwner, isValidPasswordObserver)
            isRegistered.observe(viewLifecycleOwner, isRegisteredObserver)
        }
    }

    private val buttonLoginListener = View.OnClickListener {
        it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private val buttonToRegisterListener = View.OnClickListener {
        registerViewModel.completedForm(
            binding.etRegisterUserPassword.text.toString(),
            binding.etRegisterUserEmail.text.toString(),
            binding.etRegisterUserName.text.toString()
        )
    }

    private val isValidEmailObserver = Observer<Boolean> { TODO("Not yet implemented") }

    private val isValidPasswordObserver = Observer<Boolean> { TODO("Not yet implemented") }

    private val isRegisteredObserver = Observer<RegisterViewModel.RegisteredState> {
        if (it == RegisterViewModel.RegisteredState.REGISTERED){
            Toast.makeText(context, "Email is already in use", Toast.LENGTH_SHORT).show()
        }
    }

    private val emailListener = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }

    private val passwordListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    private val nameListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }



}