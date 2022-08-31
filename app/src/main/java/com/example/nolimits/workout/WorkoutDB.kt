package com.example.nolimits.workout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val workoutName: String,
    val workoutDuration: Int
)