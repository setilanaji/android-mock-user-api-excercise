package com.ydh.androiuserapi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ydh.androiuserapi.R
import com.ydh.androiuserapi.databinding.FragmentHomeBinding
import com.ydh.androiuserapi.model.Page
import com.ydh.androiuserapi.ui.adapter.ViewPagerAdapter


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var menuItem: MenuItem

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        setupViewPager()
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.userFragment -> binding.viewpager.currentItem = 0
                R.id.favoriteFragment -> binding.viewpager.currentItem = 1
                R.id.profileFragment -> binding.viewpager.currentItem = 2
            }
            return@setOnNavigationItemSelectedListener false
        }
        return binding.root
    }

    private fun setupViewPager() {
       binding.run {
           val pages = listOf(
                   Page("Users", UserFragment()),
                   Page("Favorite", FavoriteFragment()),
                   Page("Profile", ProfileFragment()))
           val adapter = ViewPagerAdapter(pages, childFragmentManager, lifecycle)
           viewpager.adapter = adapter

           viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
               override fun onPageSelected(position: Int) {
                   bottomNavigationView.menu.getItem(0).isChecked = false
                   bottomNavigationView.menu.getItem(position).isChecked = true
                   menuItem = bottomNavigationView.menu.getItem(position)
               }
           })
       }
    }



}