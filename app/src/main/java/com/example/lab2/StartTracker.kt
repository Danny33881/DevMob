package com.example.lab2

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator

class StartTracker : AppCompatActivity() {
    lateinit var myVM : MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_tracker)

        val dataset = arrayOf("Велосипед", "Бег", "Шаг")
        val customAdapter = TypeRecyclerViewAdapter(dataset)

        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = customAdapter
        (recyclerView.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false

        myVM = ViewModelProvider(this).get(MyViewModel :: class.java)

        findViewById<Button>(R.id.start).setOnClickListener{
//            val typeActivity =
        }
    }

}