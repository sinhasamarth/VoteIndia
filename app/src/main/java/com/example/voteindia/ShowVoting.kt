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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import open.git.votingmainpainel.adapter
import open.git.votingmainpainel.tempInfo
import open.git.votingmainpainel.tempPosition


class ShowVoting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_voting)
        var recyclerview=findViewById<RecyclerView>(R.id.recycler_views)
        recyclerview.adapter=adapter(getdata())
        recyclerview.layoutManager=LinearLayoutManager(this)

    }
    private fun getdata(): ArrayList<Item>{
        var TempArray=ArrayList<Item>(0)
        var name= arrayOf("Narendra Modi","Rahul Gandhi","Nitish Kumar")
        var pNames = arrayOf("BJP","INC","JDU")
        var cphoto = arrayOf(R.drawable.modi,R.drawable.rahul,R.drawable.nitishkumar)
        var pphoto = arrayOf(R.drawable.modilogo,R.drawable.congresslogo,R.drawable.jdulogo)
        for(i in 0..2)
        {
            TempArray.add(Item(name[i],pNames[i],cphoto[i],pphoto[i]))
        }
        return TempArray

    }


    fun ConfirmVoting(view: View) {
        var alert= AlertDialog.Builder(this)
        alert.setTitle("CONFIRM VOTE")
        alert.setMessage("You are going to vote ${tempInfo[tempPosition].CandidateName} of Party ${tempInfo[tempPosition].PartyName}. Are you sure?")
        alert.setPositiveButton("VOTE"){dialogInterface, which ->
            Toast.makeText(this,"You have voted ${tempInfo[tempPosition].CandidateName} of Party ${tempInfo[tempPosition].PartyName}.", Toast.LENGTH_LONG).show()

            finish()
        }
        alert.setNegativeButton("CHOOSE AGAIN"){dialogInterface, which ->
            Toast.makeText(this,"Please vote ", Toast.LENGTH_LONG).show()
        }
        val alertbox: AlertDialog = alert.create()
        alertbox.show()
    }

}