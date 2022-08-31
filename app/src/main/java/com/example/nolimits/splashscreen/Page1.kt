package com.example.nolimits.splashscreen

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nolimits.MainActivity
import com.example.nolimits.R
import com.example.nolimits.databinding.ActivityPage1Binding
import com.example.nolimits.home.Home
import com.google.firebase.auth.FirebaseAuth

class Page1 : AppCompatActivity() {

    lateinit var binding: ActivityPage1Binding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPage1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val animDrawable = binding.page1Layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(200)
        animDrawable.start()

        binding.next1.setOnClickListener {
            val intent = Intent(this, Page2::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right_w, R.anim.slide_out_left_w)
        }
        binding.skip1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right_w, R.anim.slide_out_left_w)
        }
    }
    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            Intent(this, Home::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

    }
}