package com.example.nolimits.workout

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class WorkoutViewModel @Inject constructor(
    workoutRepository: WorkoutRepository
): ViewModel() {
    val readAll = workoutRepository.readAll
}