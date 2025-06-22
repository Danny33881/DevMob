package com.example.lab2

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Depends.context = applicationContext
        Depends.initDatabase()
    }
}