package com.ydh.androiuserapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ydh.androiuserapi.util.App
import com.ydh.androiuserapi.R
import com.ydh.androiuserapi.UserSession
import com.ydh.androiuserapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController
    lateinit var binding: ActivityMainBinding

    private val prefs: UserSession by lazy {
        UserSession(App.instance)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(R.layout.activity_main)
        navigationController = this.findNavController(R.id.navHostFragment)
//        setNavigation()

        if (!prefs.loggedIn){
            navigationController.navigate(R.id.onboardingFragment)
        }else{
            navigationController.navigate(R.id.homeFragment)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.id.navHostFragment)
//        return NavigationUI.navigateUp(navController)
//    }
}