package com.example.tesing.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tesing.R
import com.example.tesing.models.ProductsModel
import com.google.firebase.database.FirebaseDatabase

class AddDetailsActivity:AppCompatActivity() {
    private lateinit var tvId: TextView
    private lateinit var tvname: TextView
    private lateinit var tvmobile: TextView
    private lateinit var tvTopic: TextView
    private lateinit var tvdescription: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_details)
        initView()
        setValuesToView()
        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("postId").toString(),
                intent.getStringExtra("name").toString()
            )
        }
        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("postId").toString()
            )
        }
    }

    private fun deleteRecord(
        Id: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Products").child(Id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Your post has deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()


        }
    }

    private fun initView() {

        tvId = findViewById(R.id.tvId)
        tvname = findViewById(R.id.tvname)
        tvmobile = findViewById(R.id.tvmobile)
        tvTopic = findViewById(R.id.tvTopic)
        tvdescription = findViewById(R.id.tvdescription)


        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }


    private fun setValuesToView() {
        tvId.text = intent.getStringExtra("postId")
        tvname.text = intent.getStringExtra("name")
        tvmobile.text = intent.getStringExtra("mobile")
        tvTopic.text = intent.getStringExtra("addTopic")
        tvdescription.text = intent.getStringExtra("description")

    }

    private fun openUpdateDialog(
        postId: String,
        name: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_post, null)

        mDialog.setView(mDialogView)

        val nameEditText = mDialogView.findViewById<EditText>(R.id.name)
        val mobile = mDialogView.findViewById<EditText>(R.id.mobile)
        val addTopic = mDialogView.findViewById<EditText>(R.id.addTopic)
        val description = mDialogView.findViewById<EditText>(R.id.description)

        val btnUpdate = mDialogView.findViewById<Button>(R.id.btnUpdate)

        nameEditText.setText(intent.getStringExtra("name").toString())
        mobile.setText(intent.getStringExtra("mobile").toString())
        addTopic.setText(intent.getStringExtra("addTopic").toString())
        description.setText(intent.getStringExtra("description").toString())

        mDialog.setTitle("Updating $name Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdate.setOnClickListener {
            updatePostData(
                postId,
                nameEditText.text.toString(),
                mobile.text.toString(),
                addTopic.text.toString(),
                description.text.toString()
            )

            Toast.makeText(applicationContext, "Your Data Updated", Toast.LENGTH_LONG).show()

            // Set updated data to textviews
            tvname.text = nameEditText.text.toString()
            tvmobile.text = mobile.text.toString()
            tvTopic.text = addTopic.text.toString()
            tvdescription.text = description.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updatePostData(
        postId: String,
        toString: String,
        toString1: String,
        toString2: String,
        toString3: String
    ) {

    }
}





