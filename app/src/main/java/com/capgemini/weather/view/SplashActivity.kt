package com.capgemini.weather.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.capgemini.weather.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        supportActionBar?.hide()

      Handler().postDelayed({
         val i = Intent(this,MainActivity::class.java)
          //val i = Intent(this,NavigationActivity::class.java)
          startActivity(i)
         finish()
        },5000)
    }




}