package com.example.nolimits.food

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import com.example.nolimits.R
import com.example.nolimits.bmi.BMICalculator
import com.example.nolimits.databinding.ActivityFoodBinding
import com.example.nolimits.home.Home
import com.example.nolimits.measures.Measures
import com.example.nolimits.workout.Workout

class Food : AppCompatActivity() {
    private lateinit var binding: ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animDrawable = binding.food.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()

        binding.workoutFragment.setOnClickListener {
            startActivity(Intent(this, Workout::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
        binding.homeFragment.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
        binding.measuresFragment.setOnClickListener {
            startActivity(Intent(this, Measures::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
        binding.bmiFragment.setOnClickListener {
            startActivity(Intent(this, BMICalculator::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
        binding.f1Btn.setOnClickListener {
            if(binding.f1text.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.f1, AutoTransition())
                binding.f1text.visibility == View.VISIBLE
                binding.f1Btn.text = "EXPAND"
            } else {
                TransitionManager.beginDelayedTransition(binding.f1, AutoTransition())
                binding.f1text.visibility == View.GONE
                binding.f1Btn.text = "COMPRESS"
            }
        }
        binding.photof2.setOnClickListener {

            if(binding.f2text.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.f2, AutoTransition())
                binding.f2text.visibility == View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(binding.f2, AutoTransition())
                binding.f2text.visibility == View.GONE
            }
        }
    }
}