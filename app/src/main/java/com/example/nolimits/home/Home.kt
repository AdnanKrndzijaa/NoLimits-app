package com.example.nolimits.home

import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nolimits.MainActivity
import com.example.nolimits.NoLimitsDatabase
import com.example.nolimits.R
import com.example.nolimits.bmi.BMICalculator
import com.example.nolimits.databinding.ActivityHomeBinding
import com.example.nolimits.food.Food
import com.example.nolimits.home.nutrition.*
import com.example.nolimits.home.trainings.*
import com.example.nolimits.measures.Measures
import com.example.nolimits.settings.Settings
import com.example.nolimits.workout.Workout
import com.example.nolimits.workout.WorkoutDB
import com.example.nolimits.workout.WorkoutList
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth
    val EXTRA_TYPE = "Home/EXTRA_TYPE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animDrawable = binding.homeLayout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()

        auth = FirebaseAuth.getInstance()
        binding.logout.setOnClickListener {
            val view = View.inflate(this@Home, R.layout.layout_logout, null)

            val builder = AlertDialog.Builder(this@Home)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            val yesBtn = dialog.findViewById<TextView>(R.id.buttonLogout)
            val noBtn = dialog.findViewById<TextView>(R.id.buttonCancel)
            yesBtn.setOnClickListener {
                dialog.dismiss()
                auth.signOut()
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                    Toast.makeText(this, "See you again!", Toast.LENGTH_SHORT).show()
                }
            }
            noBtn.setOnClickListener {
                dialog.dismiss()
            }
        }
        binding.settings.setOnClickListener {
            startActivity(Intent(this, Settings::class.java))
        }
        binding.training1.setOnClickListener {
            startActivity(Intent(this, StrengthTraining::class.java))
        }
        binding.training2.setOnClickListener {
            startActivity(Intent(this, AerobicTraining::class.java))
        }
        binding.training3.setOnClickListener {
            startActivity(Intent(this, BalanceStabilityTraining::class.java))
        }
        binding.training4.setOnClickListener {
            startActivity(Intent(this, CoordinationAgilityTraining::class.java))
        }
        binding.training5.setOnClickListener {
            startActivity(Intent(this, FlexibilityMobilityTraining::class.java))
        }
        binding.food1.setOnClickListener {
            startActivity(Intent(this, HealthyEating::class.java))
        }
        binding.food2.setOnClickListener {
            startActivity(Intent(this, LoseFat::class.java))
        }
        binding.food3.setOnClickListener {
            startActivity(Intent(this, GainMass::class.java))
        }
        binding.food4.setOnClickListener {
            startActivity(Intent(this, Supplements::class.java))
        }
        binding.food5.setOnClickListener {
            startActivity(Intent(this, PerformanceNutrition::class.java))
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
        binding.bmiFragment.setOnClickListener {
            startActivity(Intent(this, BMICalculator::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
        binding.ex1.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
        binding.ex2.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
        binding.ex3.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
        binding.ex4.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
        binding.ex5.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
        binding.ex6.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
        binding.ex7.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
    }
}