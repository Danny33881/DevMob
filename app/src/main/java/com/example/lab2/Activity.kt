package com.example.lab2

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class activityType(val displayName: String){
    RUNNING("Бег"),
    BICYCLE("Велосипед"),
    WALKING("Шаг")
}

@Entity
data class Activity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val user: String,
    val type: activityType,
    val startTime: Long = System.currentTimeMillis(),
    val endTime: Long = System.currentTimeMillis() + 300000
) : java.io.Serializable