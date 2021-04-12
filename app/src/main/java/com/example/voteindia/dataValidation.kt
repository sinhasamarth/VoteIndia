package com.example.voteindia

import android.util.Log
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.text.toUpperCase as textToUpperCase

class dataValidation {
    companion object{
         var phoneNoss:String = "Fails"
        fun validataeData( Name:String , UIDNo :String , Pincode:String):String{
            val fire = FirebaseDatabase.getInstance().getReference("MainDatabase")
            fire.child("$Pincode/$UIDNo").get().addOnSuccessListener {
                if(it.exists())
                {
                    if (Name.textToUpperCase(Locale.ROOT)
                            ==
                            it.child("Name").value.toString().textToUpperCase(Locale.ROOT))
                    {
                        getPhoneNo(it.child("PhoneNo").value.toString())
                    }
                }

            }

            return phoneNoss
    }
        fun getPhoneNo(p:String){
            phoneNoss = p
        }
    }
}
