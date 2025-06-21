package com.example.lab2

import android.os.Parcelable
import android.os.Parcel

data class Activity(
    val length: String,      // дистанция (например "14.32 км")
    val time: String,        // продолжительность (например "2 часа 46 минут")
    val name: String,        // название активности (например "Серфинг")
    val whenWas: String,      // когда была (например "14 часов назад")
    val user: String
) : java.io.Serializable