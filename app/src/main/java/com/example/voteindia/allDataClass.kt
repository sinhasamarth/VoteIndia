package com.example.voteindia

data class userData(
        val NameofUser: String ,
        val UIDNo:String,
        val PincodeUser:String,
        val PhoneNumberUser:String,
        val isVoted:Boolean
        )

data class Maindata(val CandName :String,
                    val PartyName:String,
                    val PartyLogo:String,
                    val CandidiateImage :String,
                    val CandUiD:String
)