package com.example.voteindia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.voteindia.databinding.ActivityOtpScreenBinding

private lateinit var binding: ActivityOtpScreenBinding
private var otpSendFirstTime:Boolean = false
class otpScreen() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val PhoneNo = intent.getStringExtra("PhoneNoUser").toString()
        binding.otpNoDisaply.text = "The Otp has been Succesfully send to \n +91 - $PhoneNo"
        binding.resendTimer.visibility = View.INVISIBLE

        binding.resendOtpBtn.visibility = View.INVISIBLE
        coundownTimerStarter()
        binding.resendOtpBtn.setOnClickListener{
            coundownTimerStarter()
        }

    }

    fun coundownTimerStarter(){
        otpSendFirstTime = true
        binding.resendOtpBtn.visibility = View.INVISIBLE
        binding.resendTimer.visibility = View.VISIBLE
        val timerObj = object : CountDownTimer(10000,1000)
        {

            override fun onTick(millisUntilFinished: Long) {
                binding.resendTimer.text = "seconds remaining: " + (millisUntilFinished /1000)%60;

            }

            override fun onFinish() {
               binding.resendTimer.visibility = View.INVISIBLE
                binding.resendOtpBtn.visibility = View.VISIBLE
            }

        }.start()

    }
}