package com.example.nolimits.home.trainings

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nolimits.R
import com.example.nolimits.databinding.ActivityCoordinationAgilityTrainingBinding
import com.example.nolimits.home.Home

class CoordinationAgilityTraining : AppCompatActivity() {
    lateinit var binding: ActivityCoordinationAgilityTrainingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinationAgilityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animDrawable = binding.cat.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()

        binding.backIcon.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }
    }
}