package com.example.nolimits.splashscreen

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nolimits.MainActivity
import com.example.nolimits.R
import com.example.nolimits.databinding.ActivityPage3Binding

class Page3 : AppCompatActivity() {
    private lateinit var binding: ActivityPage3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPage3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val animDrawable = binding.page3Layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(200)
        animDrawable.start()

        binding.next3.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right_w, R.anim.slide_out_left_w)
        }
        binding.skip3.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right_w, R.anim.slide_out_left_w)
        }
    }
}