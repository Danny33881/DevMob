package com.example.lab2

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.lab2.databinding.SecondScreenBinding


class SecondScreen : AppCompatActivity() {
    private lateinit var binding: SecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = SecondScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val text = binding.privacyPolicyText.text.toString()

        val spannedText = SpannableString(text)

        val clickable = Click()
//        val clickable2 = Click()

        spannedText.setSpan(clickable, 37, 66, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        spannedText.setSpan(clickable2, 120, 150, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.privacyPolicyText.text = spannedText


        binding.iconsArrow.setOnClickListener {
            val intent = Intent(this, FirstScreen::class.java)
            startActivity(intent)
        }

    }

}
