    package com.example.voteindia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.voteindia.databinding.ActivityLoginScreenBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

private lateinit var binding: ActivityLoginScreenBinding
class loginScreen : AppCompatActivity() {

    var checkAllConditions = arrayOf(false, false , false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Getting All the Alyout Id's

        val getuid = binding.getUidNo
        val getName = binding.getName
        val getPincode = binding.getPinCode
        val sendOtpButton = binding.sendOtpButton

        sendOtpButton.isEnabled = false
        // Error Check Section for UID , Name and PINCODE
        //---------------------------------------

        getName.doOnTextChanged { text, start, before,count ->

            checkAllConditions[2] = text!!.length > 2
            onOtpBtn()
        }

        getuid.doOnTextChanged { text, start, before, count ->
            if(text!!.length<12)
            {
                getuid.error="Enter Full UID Number"
                checkAllConditions[0] = false

            }
            else
            {
                checkAllConditions[0] = true
                getuid.error=null
            }
            onOtpBtn()
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
            onOtpBtn()
        }
        //Error Check section finished
        //-----------------------------
        sendOtpButton.setOnClickListener{
            val Layoutinflater = LayoutInflater.from(this)
                .inflate(R.layout.waiting_screen, null, false)
            val fire = FirebaseDatabase.getInstance().getReference("MainDatabase")
            fire.child("${getPincode.text.toString()}/${getuid.text.toString()}").get().addOnSuccessListener {
                if(it.exists())
                {
                    val alertDialog = MaterialAlertDialogBuilder(this)

                    alertDialog.setView(Layoutinflater)
                        .setCancelable(false)
                        .show()
                    Toast.makeText(this,
                        "${it.child("PhoneNo").value.toString()}",
                        Toast.LENGTH_SHORT)
                        .show()
                    goToOtpSec(it.child("PhoneNo").value.toString())
                }
                else{
                    Toast.makeText(this,"Fail",Toast.LENGTH_SHORT).show()
                }

            }.addOnFailureListener {
                Toast.makeText(this,"Fail",Toast.LENGTH_SHORT).show()
            }

            }
    }


    private fun onOtpBtn() {
        if(
            checkAllConditions[0] &&
            checkAllConditions[1]&&
            checkAllConditions[2]
        )

        {
            binding.sendOtpButton.isEnabled = true
        }
    }
    private fun goToOtpSec(phoneNo :String){
        val i = Intent(this, otpScreen::class.java)
        i.putExtra("UIDNo" , binding.getUidNo.text.toString())
        i.putExtra("UserPincode" , binding.getPinCode.text.toString())
        i.putExtra("UserName" , binding.getName.text.toString())
        i.putExtra("PhoneNoUser" , phoneNo)


        startActivity(i)
        finish()
    }

}