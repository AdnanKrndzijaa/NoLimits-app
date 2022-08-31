package com.example.nolimits.bmi

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.nolimits.R
import com.example.nolimits.databinding.ActivityBmicalculatorBinding
import com.example.nolimits.food.Food
import com.example.nolimits.home.Home
import com.example.nolimits.measures.Measures
import com.example.nolimits.workout.Workout

class BMICalculator : AppCompatActivity() {
    lateinit var binding: ActivityBmicalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmicalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animDrawable = binding.bmi.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()

        val weightText = findViewById<EditText>(R.id.weightInput)
        val heightText = findViewById<EditText>(R.id.heightInput)
        val calculateBtn = findViewById<TextView>(R.id.calculateBtn)

        calculateBtn.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                val bmi2digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2digits)
            }
        }

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
        binding.measuresFragment.setOnClickListener {
            startActivity(Intent(this, Measures::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
        binding.homeFragment.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
    }

    private fun validateInput(weight: String?, height: String?): Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty!", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty!", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> return true
        }
    }

    private fun displayResult(bmi: Float) {
        val resultIndex = findViewById<TextView>(R.id.bmiValue)
        val resultDescription = findViewById<TextView>(R.id.bmiType)

        resultIndex.text = bmi.toString()

        var resultText = ""
        var color = 0

        when {
            bmi < 18.50 -> {
                resultText = "Underweight"
                color = R.color.blue_warning
            }
            bmi in 18.50..24.99 -> {
                resultText = "Normal"
                color = R.color.green_good
            }
            bmi in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.yellow_warning
            }
            bmi in 30.00..34.99 -> {
                resultText = "Obese"
                color = R.color.red_warning
            }
            bmi > 35.00 -> {
                resultText = "Extreme obese"
                color = R.color.red_error
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText
    }
}