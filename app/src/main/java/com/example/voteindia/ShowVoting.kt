package com.example.voteindia

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import open.git.votingmainpainel.adapter

var isselected = false
var selectedno:Int  = -1
private lateinit var Btn : Button
private lateinit var recylerview: RecyclerView
var tempArray = ArrayList<Maindata>(3)

class ShowVoting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_voting)
        Btn = findViewById(R.id.btn)
        recylerview = findViewById(R.id.recylerViewMain)
        recylerview.layoutManager = LinearLayoutManager(this)
        recylerview.adapter = adapter(getdata())

    }

    private fun getdata(): ArrayList<Maindata> {

        val ref = FirebaseDatabase.getInstance().getReference("LiveVoting/800009")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot!!.exists())
                {
                    val p = snapshot.childrenCount
                    for (i in 1..(p))
                    {
                        val temp1 = snapshot.child("cand$i")
                        val PartyName = temp1.child("PartyName").value.toString()
                        val UIDcand = temp1.child("UIDCAND").value.toString()
                        val CandLogo = temp1.child("CandLogo").value.toString()
                        val PartyLogo = temp1.child("PartyLogo").value.toString()
                        val Name = temp1.child("Name").value.toString()
                        val m = Maindata(
                                Name,PartyName,PartyLogo,CandLogo,UIDcand
                        )
                        Log.d("jjj" , m.toString())
                        tempArray.add(m)

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        Log.d("HII", tempArray.toString())
        return tempArray

    }


    companion object{
        fun resetColor(data:Int)
        {
            recylerview.findViewHolderForAdapterPosition(data)?.itemView?.findViewById<LinearLayout>(R.id.cardviewMain)?.setBackgroundColor(Color.WHITE)
            Log.d("HII","$data")

        }
        fun checkBtn() {
            if (isselected) {
                Btn.isEnabled = true
            }
        }

    }

}