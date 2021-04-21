package com.example.voteindia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.voteindia.databinding.ActivityVotingDoneBinding


class VotingDone : AppCompatActivity() {
    private lateinit var binding: ActivityVotingDoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVotingDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.DisplayPartyName.setText(
                "From Party - "+ intent.getStringExtra("PartyName").toString()
        )

        binding.DisplayUserName.setText(
                "Hey  - "+ intent.getStringExtra("UserName").toString()
        )

        binding.DisplayCandidateName.setText(
                "From Party - "+ intent.getStringExtra("CandName").toString()
        )
    }
}