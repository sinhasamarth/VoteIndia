package com.example.voteindia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.voteindia.databinding.ActivityErrorBinding

class Error : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.errorText.setText(
            intent.getStringExtra("ErrorMsg").toString()
        )

        binding.retryBtn.setOnClickListener {
            startActivity(Intent(this,loginScreen::class.java))
        }
    }
}