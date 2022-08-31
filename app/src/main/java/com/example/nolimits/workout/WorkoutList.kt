package com.example.nolimits.workout

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.nolimits.NoLimitsDatabase
import com.example.nolimits.R
import com.example.nolimits.databinding.ActivityWorkoutListBinding

class WorkoutList : AppCompatActivity() {
    lateinit var binding: ActivityWorkoutListBinding
    val EXTRA_EX_ID = "WorkoutList/EXTRA_EX_ID"
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras

        val animDrawable = binding.workout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()

        listView = findViewById<ListView>(R.id.workoutListView)
        setupListAdapter(extras?.getString("EXTRA_TYPE").toString())
        listView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val todoId = parent.getItemIdAtPosition(position)
            val intent = Intent(this@WorkoutList, SelectedWorkout::class.java)
            intent.putExtra(EXTRA_EX_ID, todoId)
            startActivity(intent)
        })
    }
    private fun setupListAdapter(type: String) {
        val work: List<WorkoutDB> = NoLimitsDatabase.getDatabase(this).workoutDAO().getAll()
        val adapter = WorkoutAdapter(this, work)
        listView.setAdapter(adapter)
    }
}