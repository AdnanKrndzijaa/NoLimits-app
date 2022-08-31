package com.example.nolimits.workout

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkoutDAO {
    @Query("SELECT * FROM workouts")
    fun getAll(): List<WorkoutDB>

    @Query("SELECT * FROM workouts WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<WorkoutDB>

    @Query("SELECT * FROM workouts WHERE type = :type ORDER BY exName ASC")
    fun getByType(type: String?): LiveData<List<WorkoutDB?>>

    @Insert
    fun add(workoutDB: WorkoutDB?)

    @Delete
    fun delete(workoutDB: WorkoutDB?)

    @Query("DELETE FROM workouts")
    fun deleteAll()
}