package com.example.lab2

data class ActivityStatic(
    val length: String,      // дистанция (например "14.32 км")
    val time: String,        // продолжительность (например "2 часа 46 минут")
    val name: String,        // название активности (например "Серфинг")
    val whenWas: String,      // когда была (например "14 часов назад")
    val user: String
) : java.io.Serializable