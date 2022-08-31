package com.example.nolimits

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nolimits.workout.WorkoutDAO
import com.example.nolimits.workout.WorkoutDB

@Database(entities = [WorkoutDB::class], version = 1, exportSchema = false)
abstract class NoLimitsDatabase : RoomDatabase() {

    abstract fun workoutDAO(): WorkoutDAO

    companion object {
        @Volatile
        private var INSTANCE: NoLimitsDatabase? = null

        fun getDatabase(context: Context): NoLimitsDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoLimitsDatabase::class.java,
                    "NoLimitsDB"
                ).createFromAsset("database/workout").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}