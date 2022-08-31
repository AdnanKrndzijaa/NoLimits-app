package com.example.nolimits.workout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import com.example.nolimits.R

class WorkoutAdapter(workoutList: WorkoutList, work: List<WorkoutDB>) : BaseAdapter() {
    private var context: Context? = null
    private var workoutDBList: List<WorkoutDB>? = null

    fun DrugsAdapter(context: Context?, workoutDBList: List<WorkoutDB>?) {
        this.context = context
        this.workoutDBList = workoutDBList
    }

    override fun getCount(): Int {
        return workoutDBList!!.size
    }

    override fun getItem(i: Int): Any? {
        return workoutDBList!![i]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View, viewGroup: ViewGroup?): View? {
        var view = view
        val inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.workout_item, viewGroup, false)
        val workoutDB: WorkoutDB = workoutDBList!![i]
        //val imageView = view.findViewById<ImageView>(R.id.workoutPhoto)
        val name = view.findViewById<TextView>(R.id.nameW)
        val duration = view.findViewById<TextView>(R.id.durationW)
        //imageView.setImageResource(workoutDB.image)
        name.setText(workoutDB.workoutName)
        duration.setText(workoutDB.workoutDuration)
        return view
    }
}