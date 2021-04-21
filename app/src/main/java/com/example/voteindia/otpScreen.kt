package com.example.voteindia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.voteindia.databinding.ActivityOtpScreenBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


private lateinit var binding: ActivityOtpScreenBinding
private var otpSendFirstTime:Boolean = false
private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
private lateinit var resendTocken:PhoneAuthProvider.ForceResendingToken
private lateinit var verification_Id:String
private lateinit var PhoneNo:String
class otpScreen() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        Setting Up the Phone o in UI
         PhoneNo = intent.getStringExtra("PhoneNoUser").toString()
        binding.otpNoDisaply.text = "The Otp has been Succesfully send to \n +91 - $PhoneNo"
        binding.resendTimer.visibility = View.INVISIBLE
        binding.resendOtpBtn.visibility = View.INVISIBLE
        coundownTimerStarter()
        createcallacks()
        sendOtp()
        binding.OtpmainBtn.setOnClickListener{
            val code = binding.getOtp.text.toString()
                Log.d("OTP Final",code)
                val credential = PhoneAuthProvider.getCredential(verification_Id,code)
                signInWithPhoneAuthCredential(credential)
        }


    }

    private fun sendOtp() {
        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber("+91"+ PhoneNo)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    private fun createcallacks() {
        callbacks =  object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                if(p0.smsCode.isNullOrEmpty()){
                    Log.d("OTP",p0.smsCode.toString())
                    binding.otpNoDisaply.text = p0.smsCode.toString()
                    signInWithPhoneAuthCredential(p0)
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
               Log.d("Error",p0.toString())
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                resendTocken = p1
                verification_Id = p0
                Log.d("OTP",p0)
                Toast.makeText(this@otpScreen,"Code Send",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun resendCode( resendTocken:PhoneAuthProvider.ForceResendingToken) {
        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                .setPhoneNumber("+91"+ PhoneNo)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
                .setForceResendingToken(resendTocken)
                .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(p0: PhoneAuthCredential) {
        FirebaseAuth.getInstance()
            .signInWithCredential(p0)
            .addOnCompleteListener(this) {task ->
                if(task.isSuccessful)
                {
                    val nextActivity = Intent(this,ShowVoting::class.java)
                    val PincodeOfUser = intent.getStringExtra("UserPincode").toString()
                    nextActivity.putExtra("Pincodess",PincodeOfUser)
                    nextActivity.putExtra("Name",intent.getStringExtra("UserName").toString())
                    nextActivity.putExtra("UID",intent.getStringExtra("UIDNo").toString())
                    Log.d("USernameOtp",intent.getStringExtra("UserName").toString())
                    startActivity(nextActivity)
                }
                else{
                    // Sign in failed, display a message and update the UI
                    Log.w("Error", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(this,"Incorrect OTP",Toast.LENGTH_SHORT).show()
                    }
                }

            }
    }

    fun coundownTimerStarter(){
        otpSendFirstTime = true
        binding.resendOtpBtn.visibility = View.INVISIBLE
        binding.resendTimer.visibility = View.VISIBLE
        object : CountDownTimer(60000,1000)
        {

            override fun onTick(millisUntilFinished: Long) {
                binding.resendTimer.text = "seconds remaining: " + (millisUntilFinished /1000)%60

            }

            override fun onFinish() {
               binding.resendTimer.visibility = View.INVISIBLE
                binding.resendOtpBtn.visibility = View.VISIBLE
            }

        }.start()

    }
}