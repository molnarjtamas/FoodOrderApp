package com.example.foodorderapp.home.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodorderapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}