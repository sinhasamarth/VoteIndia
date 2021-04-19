package com.example.voteindia

data class userData(
        val NameofUser: String ,
        val UIDNo:String,
        val PincodeUser:String,
        val PhoneNumberUser:String,
        val isVoted:Boolean
        )

data class Item(var CandidateName:String,
                    var PartyName:String,
                    var CandidatePhoto:Int,
                    var PartyLogo:Int,

)