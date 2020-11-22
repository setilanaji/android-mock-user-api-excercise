package com.ydh.androiuserapi.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
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
import androidx.navigation.fragment.findNavController
import com.ydh.androiuserapi.R
import com.ydh.androiuserapi.databinding.FragmentLoginBinding
import com.ydh.androiuserapi.viewmodel.LoginViewModel
import com.ydh.androiuserapi.viewmodel.factory.LoginViewModelFactory


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var loginViewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container, false)

        setViewModel()
//        setView()
        setListener()
        setObserver()

        return binding.root
    }

    private fun setListener(){
        binding.etLoginUserEmail.addTextChangedListener(emailListener)
        binding.etLoginUserPassword.addTextChangedListener(passwordListener)
        binding.btLogLogin.setOnClickListener(buttonLoginListener)
        binding.btLogRegister.setOnClickListener(buttonToRegisterListener)
    }

    private fun setViewModel(){
        loginViewModel =   ViewModelProviders.of(this, LoginViewModelFactory(this.context)).get(
            LoginViewModel::class.java)
    }

    private fun setObserver(){
        loginViewModel.apply {
            isEmailValid.observe(viewLifecycleOwner, isValidEmailObserver)
            isPasswordValid.observe(viewLifecycleOwner, isValidPasswordObserver)
            isLogged.observe(viewLifecycleOwner, isLoggedObserver)
        }
    }

    private val buttonLoginListener = View.OnClickListener {
        loginViewModel.completedForm( binding.etLoginUserPassword.text.toString(), binding.etLoginUserEmail.text.toString())
    }

    private val buttonToRegisterListener = View.OnClickListener {
        it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
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

    private val isValidEmailObserver = Observer<Boolean> { TODO("Not yet implemented") }

    private val isValidPasswordObserver = Observer<Boolean> { TODO("Not yet implemented") }

    private val isLoggedObserver =
        Observer<LoginViewModel.LoginState> { t ->
            when(t){
                LoginViewModel.LoginState.LOGGED -> {
                    this.findNavController().navigate(R.id.homeFragment)
                }
            }
        }

    // set Email error
    private fun setEmailError(s: Editable?){
        binding.tlLoginEmail.error = when{
            TextUtils.isEmpty(s.toString()) -> "email must not be empty"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches() -> "email format is not correct"
            else -> null
        }
    }

}