package com.example.voteindia

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import open.git.votingmainpainel.adapter
import open.git.votingmainpainel.tempInfo
import open.git.votingmainpainel.tempPosition
import android.widget.Adapter as Adapter


class ShowVoting : AppCompatActivity() {
    private lateinit var Dbreference: DatabaseReference
    private lateinit var CandidatesDeatilsArray: ArrayList<Item>
    private lateinit var  recyclerview: RecyclerView
    private lateinit var  Pincode: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_voting)

         recyclerview=findViewById(R.id.recycler_views)
        recyclerview.layoutManager=LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        getdata()


    }
    private fun getdata(){

        Pincode = intent.getStringExtra("Pincodess").toString()
        Dbreference = FirebaseDatabase.getInstance().getReference("LiveVoting/$Pincode")
        var tempArray=ArrayList<Item>(0)

        Dbreference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for (mySnap in snapshot.children)
                    {
                        val temp = mySnap.getValue(Item::class.java)
                        Log.d("Datass", temp.toString())
                        tempArray.add(temp!!)
                    }
                }
                Log.d("xxxxoo","$tempArray")
                recyclerview.adapter = adapter(tempArray)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }


    fun ConfirmVoting(view: View) {
        var alert= AlertDialog.Builder(this)
        alert.setTitle("CONFIRM VOTE")
        alert.setMessage("You are going to vote ${tempInfo[tempPosition].CandidateName} of Party ${tempInfo[tempPosition].PartyName}. Are you sure?")
        alert.setPositiveButton("VOTE"){dialogInterface, which ->

                val uid = intent.getStringExtra("UID").toString()
                val CandUID = tempInfo[tempPosition].CandidateUID
                val candName = tempInfo[tempPosition].CandidateName
                val candParty = tempInfo[tempPosition].PartyName
                val UserName = intent.getStringExtra("Name").toString()
                Log.d("UsernAme ", UserName)
            VoteNow(uid, CandUID ,candName,candParty,Pincode,UserName )
        }
        alert.setNegativeButton("CHOOSE AGAIN"){dialogInterface, which ->
            Toast.makeText(this,"Please vote ", Toast.LENGTH_LONG).show()
        }
        val alertbox: AlertDialog = alert.create()
        alertbox.show()
    }
    private fun VoteNow(uid: String,
                        candUID: String?,
                        candName: String?,
                        candParty: String?,
                        Pincode:String?,
                        UserName:String?)
    {
        val Dbref = FirebaseDatabase.getInstance().getReference("VoteCounter/$Pincode")
            Log.d("UID", UserName.toString())
        Dbref.child("$uid").get().addOnSuccessListener {
            if (it.exists()  && it.child("candParty").value != null && it.childrenCount > 0   )
            {
                Toast.makeText(this ,"You Have Already Voted ", Toast.LENGTH_LONG).show()
                finish()
            }
            else
            {
                Dbref.child("$uid").setValue(VoteDetails(candUID, candName, candParty)).addOnSuccessListener {
                    val nextIntent = Intent(this, VotingDone::class.java)
                    nextIntent.putExtra("CandName", candName)
                    nextIntent.putExtra("UserName", UserName)
                    nextIntent.putExtra("PartyName", candParty)
                    startActivity(nextIntent)


                }
            Log.d("CHecking Firebasse", "$it ssss")

        }



        }

    }
    private inner class VoteDetails(var candUID: String?,var  candName: String?, var candParty: String?)


}



