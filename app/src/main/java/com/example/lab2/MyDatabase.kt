package com.example.lab2

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Activity::class], version = 4)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getActivityDao() : ActivityDao
}