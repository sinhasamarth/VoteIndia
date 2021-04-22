package com.example.voteindia

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

data class userData(
        val NameofUser: String ,
        val UIDNo:String,
        val PincodeUser:String,
        val PhoneNumberUser:String,
        val isVoted:Boolean
        )

data class Item(var CandidateName:String ? = null,
                    var PartyName:String ? = null,
                    var CandidatePhoto:String ? = null,
                    var PartyLogo:String ? = null,
                    var CandidateUID:String ? = null,

)