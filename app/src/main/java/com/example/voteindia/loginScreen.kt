package com.example.voteindia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class loginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        // Getting All the Alyout Id's
        val getuid = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.getUidNo)
        val getName = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.getName)
        val getPincode = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.getPinCode)
        val sendOtpButton = findViewById<com.google.android.material.button.MaterialButton>(R.id.sendOtpButton)
        // Get input text
    }
}