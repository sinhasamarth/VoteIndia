package com.example.voteindia

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