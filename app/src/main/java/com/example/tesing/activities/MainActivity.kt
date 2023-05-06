package com.example.tesing.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tesing.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var addBuy: Button
    private lateinit var fetchbtn: Button





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addpost)





        addBuy = findViewById(R.id.addBuy)
        fetchbtn = findViewById(R.id.fetchbtn)


        addBuy.setOnClickListener{
val intent = Intent(this, BuyRequirementActivity::class.java)
            startActivity(intent)
        }
        fetchbtn.setOnClickListener {
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()
    }
}