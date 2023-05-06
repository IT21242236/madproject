package com.example.tesing.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tesing.models.ProductsModel
import com.example.tesing.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BuyRequirementActivity: AppCompatActivity() {

    private lateinit var topic: TextView
    private lateinit var name: EditText
    private lateinit var mobile: EditText
    private lateinit var addTopic: EditText
    private lateinit var description: EditText
    private lateinit var addData: Button
    private lateinit var cancelData: Button

private lateinit  var dbRef: DatabaseReference






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buyrequirement)
        name= findViewById(R.id.name)
        mobile= findViewById(R.id.mobile)
        addTopic= findViewById(R.id.addTopic)
        description= findViewById(R.id.description)
        addData= findViewById(R.id.addData)
        cancelData= findViewById(R.id.cancelData)

        dbRef= FirebaseDatabase.getInstance().getReference("Products")

        addData.setOnClickListener {
            saveProductRequirement()
        }
    }
    private fun saveProductRequirement() {
        val name = name.text.toString()
        val mobile = mobile.text.toString()
        val addTopic = addTopic.text.toString()
        val description = description.text.toString()



        val postId = dbRef.push().key!!
        val products = ProductsModel(postId, name, mobile, addTopic, description)

        dbRef.child(postId).setValue(products).addOnCompleteListener {
            Toast.makeText(this, "Successfully posted", Toast.LENGTH_LONG).show()



        }.addOnFailureListener {
            Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_LONG).show()
        }
    }


}
