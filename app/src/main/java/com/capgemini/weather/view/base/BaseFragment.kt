package com.capgemini.weather.view.base

import android.os.Bundle
import android.view.View
import com.capgemini.weather.view.MainActivity
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment() {

    protected fun onFragmentBackPressed() {
        requireActivity().onBackPressed()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).setStatusBarGreen()

        try {
            (requireActivity() as MainActivity).supportActionBar!!.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}