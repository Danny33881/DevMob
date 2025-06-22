package com.example.lab2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.lab2.databinding.ActivityProfileBinding


class ActivityProfile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    lateinit var myVM : MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)

        myVM = ViewModelProvider(this).get(MyViewModel::class.java)

        val fragment1 = ActivityFragment.newInstance("", "")
        val fragment2 = ProfileFragment.newInstance("", "")

        supportFragmentManager.beginTransaction().apply{
            add(R.id.fragmentContainerView, fragment1, "firstFragment")
            add(R.id.fragmentContainerView, fragment2, "secondFragment")
            hide(fragment2)
            commit()
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_activity ->{
                    supportFragmentManager.beginTransaction().apply{
                        hide(fragment2)
                        show(fragment1)
                        commit()
                    }
                    true
                }
                R.id.action_profile -> {
                    supportFragmentManager.beginTransaction().apply{
                        hide(fragment1)
                        show(fragment2)
                        commit()
                    }
                    true
                }
                else -> false
            }
        }

        binding.start.setOnClickListener(){
            val intent = Intent(this, StartTracker::class.java)
            startActivity(intent)
        }
    }
}