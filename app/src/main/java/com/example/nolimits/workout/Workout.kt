package com.example.nolimits.workout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.AnimationDrawable
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.nolimits.R
import com.example.nolimits.bmi.BMICalculator
import com.example.nolimits.databinding.ActivityWorkoutBinding
import com.example.nolimits.food.Food
import com.example.nolimits.home.Home
import com.example.nolimits.measures.Measures
import kotlin.math.roundToInt

class Workout : AppCompatActivity(), SensorEventListener {
    lateinit var binding: ActivityWorkoutBinding
    var running = false
    var sensorManager: SensorManager? = null
    var stepCount = 0
    var previousStepCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        resetSteps()

        val animDrawable = binding.workouts.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()



        binding.homeFragment.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
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
        binding.fullbodyprogram.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
        binding.absprogram.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
        binding.armsprogram.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
        binding.legsprogram.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
        binding.backprogram.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
        binding.shouldersprogram.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }
        binding.chestprogram.setOnClickListener {
            Toast.makeText(this, "Section in progress. Will be soon available.", Toast.LENGTH_SHORT).show()
        }


        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        running = true
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if(stepsSensor == null) {
            Toast.makeText(this, "No step counter sensor!", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        if (running) {
            stepCount = sensorEvent!!.values[0].roundToInt()
            val totalSteps = stepCount - previousStepCount
            binding.stepNumber.text = ("$totalSteps")
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    fun resetSteps() {
        binding.stepNumber.setOnClickListener {
            Toast.makeText(this, "Long tap to reset steps", Toast.LENGTH_SHORT).show()
        }
        binding.stepNumber.setOnLongClickListener {
            previousStepCount = stepCount
            binding.stepNumber.text = 0.toString()
            saveData()

            true
        }
    }
    private fun saveData() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putFloat("key1", previousStepCount.toFloat())
        editor.apply()
    }
    private fun loadData() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedNumber: Float = sharedPreferences.getFloat("key1", 0f)
        Log.d("Workout", "$savedNumber")
        previousStepCount = savedNumber.roundToInt()
    }
}
