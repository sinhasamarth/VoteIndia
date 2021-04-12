package com.example.voteindia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText

class loginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        // Getting All the Alyout Id's

        val getuid = findViewById<TextInputEditText>(R.id.getUidNo)
        val getName = findViewById<TextInputEditText>(R.id.getName)
        val getPincode = findViewById<TextInputEditText>(R.id.getPinCode)
        val sendOtpButton = findViewById<Button>(R.id.sendOtpButton)
        var checkAllConditions = arrayOf(false, false , false)

        // Error Check Section for UID , Name and PINCODE
        //---------------------------------------

        getName.doOnTextChanged { text, start, before,count ->

            checkAllConditions[2] = text!!.length>=2
        }

        getuid.doOnTextChanged { text, start, before, count ->
            if(text!!.length<12)
            {
                getuid.error="Enter Full UID"
                checkAllConditions[0] = false

            }
            else
            {
                checkAllConditions[0] = true
                getuid.error=null
            }
        }
        getPincode.doOnTextChanged { text, start, before, count ->
            if(text!!.length<6)
            {
                getPincode.error="Enter Full PINCODE"
                checkAllConditions[1] = false
            }
            else
            {
                checkAllConditions[1] = true
                getPincode.error=null
            }
        }
        //Error Check section finished
        //-----------------------------
        sendOtpButton.setOnClickListener(){
                if(
                    checkAllConditions[0] &&
                    checkAllConditions[1]&&
                    checkAllConditions[2]
                ) {
                    val i = Intent(this, otpScreen::class.java)
                    startActivity(i)
                    finish()
                }
            }


    }



}