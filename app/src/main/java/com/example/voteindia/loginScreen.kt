package com.example.voteindia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged

class loginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        // Getting All the Alyout Id's
        val getuid = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.getUidNo)
        val getName = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.getName)
        val getPincode = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.getPinCode)
        val sendOtpButton = findViewById<com.google.android.material.button.MaterialButton>(R.id.sendOtpButton)
        // Error Check Section for UID and PINCODE
        //---------------------------------------
        getuid.doOnTextChanged { text, start, before, count ->
            if(text!!.length<12)
            {
                getuid.error="Enter Full UID"
            }
            else
            {
                getuid.error=null
            }
        }
        getPincode.doOnTextChanged { text, start, before, count ->
            if(text!!.length<6)
            {
                getPincode.error="Enter Full PINCODE"
            }
            else
            {
                getPincode.error=null
            }
        }
        //Error Check section finished
        //-----------------------------
        sendOtpButton.setOnClickListener(){
            val i= Intent(this,otpScreen::class.java)
            startActivity(i)
            finish()
        }
    }
}