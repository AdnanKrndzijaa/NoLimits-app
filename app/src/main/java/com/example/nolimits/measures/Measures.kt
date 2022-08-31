package com.example.nolimits.measures

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.nolimits.R
import com.example.nolimits.bmi.BMICalculator
import com.example.nolimits.databinding.ActivityMeasuresBinding
import com.example.nolimits.food.Food
import com.example.nolimits.home.Home
import com.example.nolimits.workout.Workout

class Measures : AppCompatActivity() {
    lateinit var binding: ActivityMeasuresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeasuresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animDrawable = binding.measures.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()


        binding.workoutFragment.setOnClickListener {
            startActivity(Intent(this, Workout::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
        binding.foodFragment.setOnClickListener {
            startActivity(Intent(this, Food::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
        binding.bmiFragment.setOnClickListener {
            startActivity(Intent(this, BMICalculator::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
        binding.homeFragment.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
    }

}