package com.example.nolimits.workout

import javax.inject.Inject

class WorkoutRepository @Inject constructor (
    private val workoutDAO: WorkoutDAO
    ){
    val readAll = workoutDAO.getAll()
}