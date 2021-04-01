package com.example.voteindia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        getSplash()
    }

    fun getSplash()
    {

        // Getting the Views From Xml

        val ashoklogo: ImageView = findViewById(R.id.ashok_image)
        val finger: ImageView = findViewById(R.id.finger)
        val text: TextView = findViewById(R.id.mainText)
        // Setting Default Value
        text.alpha = 0f
        ashoklogo.alpha = 0f

        // Adding Animations
        finger.apply {
            x = -1000f
            alpha = 0f
        }
        ashoklogo.animate().apply {
            duration = 3000
            alpha(0.5f)
            rotation(100f)
        }.start()
        finger.animate().apply {
            duration = 3000
            alpha(1f)
            x(0f)

        }.start()
        text.animate().apply {
            duration = 3000
            alpha(1f)
        }.start()

        // Intent Switcher
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        },3800)
    }

}
