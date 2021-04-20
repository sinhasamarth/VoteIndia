package com.example.voteindia

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_voting)

         recyclerview=findViewById(R.id.recycler_views)
        recyclerview.layoutManager=LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        getdata()


    }
    private fun getdata(){

       val Pincode = intent.getStringExtra("Pincodess").toString()
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
            Toast.makeText(this,
                "You have voted ${tempInfo[tempPosition].CandidateName} of Party ${tempInfo[tempPosition].PartyName}.",
                Toast.LENGTH_LONG).show()

            finish()
        }
        alert.setNegativeButton("CHOOSE AGAIN"){dialogInterface, which ->
            Toast.makeText(this,"Please vote ", Toast.LENGTH_LONG).show()
        }
        val alertbox: AlertDialog = alert.create()
        alertbox.show()
    }

}