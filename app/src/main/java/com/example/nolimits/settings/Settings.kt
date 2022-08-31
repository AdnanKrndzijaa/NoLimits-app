package com.example.nolimits.settings

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nolimits.R
import com.example.nolimits.databinding.ActivitySettingsBinding
import com.example.nolimits.home.Home

class Settings : AppCompatActivity() {
    lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animDrawable = binding.settings.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()

        binding.backIcon.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }
    }
}