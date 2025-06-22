package com.example.lab2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ActivityDao {

    @Insert
    suspend fun add(activity: Activity)

    @Delete
    suspend fun delete(activity: Activity)

    @Update
    suspend fun update(activity: Activity)

    @Query("SELECT * FROM Activity")
    fun getAllActivities(): androidx.lifecycle.LiveData<List<Activity>>
}