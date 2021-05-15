package com.capgemini.weather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.capgemini.weather.R
import com.capgemini.weather.view.base.BaseActivity
import com.capgemini.weather.view.home.HomeFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openHomeFragment()
    }

    private fun openHomeFragment() {
        val homeFragment = HomeFragment()
        replaceFragment(homeFragment)
    }

     public override fun addFragment(fragment: Fragment) {
        super.addFragment(fragment)
    }

    fun setStatusBarGreen() {
        super.setStatusBar(resources.getColor(R.color.white))
    }
}