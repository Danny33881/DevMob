package com.example.lab2

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.lab2.databinding.ThirdScreenBinding


class ThirdScreen : AppCompatActivity() {
    private lateinit var binding: ThirdScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ThirdScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.iconsArrow.setOnClickListener {
            val intent = Intent(this, FirstScreen::class.java)
            startActivity(intent)
        }

    }

}
