package com.example.lab2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch
import java.sql.Date

class MyViewModel : ViewModel() {
    private val db = Depends.db

    val allActivities = db.getActivityDao().getAllActivities()

    fun addMyActivity(typeActivity: String){
        viewModelScope.launch {
            val activity = Activity(
                user = "me",
                type =
                when(typeActivity) {
                    "Велосипед" -> activityType.BICYCLE
                    "Бег" -> activityType.RUNNING
                    "Шаг" -> activityType.WALKING
                    else -> activityType.WALKING
                },
                startTime = System.currentTimeMillis(),
                endTime = System.currentTimeMillis() + 300000
            )
            db.getActivityDao().add(activity)
        }
    }

//    fun getAllActivities(){
//        viewModelScope.launch {
//            db.getActivityDao().getAllActivities()
//        }
//    }
}