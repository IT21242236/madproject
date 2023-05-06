package com.example.tesing.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tesing.R
import com.example.tesing.adapters.ProductsAdapter
import com.example.tesing.models.ProductsModel
import com.google.firebase.database.*


class FetchingActivity: AppCompatActivity() {
    private lateinit var prorecycle: RecyclerView
    private lateinit var tvloadingData: TextView
    private lateinit var productList: ArrayList<ProductsModel>
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fetching)

        prorecycle = findViewById(R.id.rvProduct)
        prorecycle.layoutManager = LinearLayoutManager(this)
        prorecycle.setHasFixedSize(true)
        tvloadingData = findViewById(R.id.tvLoadingData)

        productList = arrayListOf<ProductsModel>()
        getProductData()
    }

    private fun getProductData() {
        prorecycle.visibility = View.GONE
        tvloadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Products")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                if(snapshot.exists()){
                    for(topicSnap in snapshot.children){
                        val productData=topicSnap.getValue(ProductsModel::class.java)
                        productList.add(productData!!)
                    }
                    val mAdapter =ProductsAdapter(productList)
                    prorecycle.adapter=mAdapter

                    mAdapter.setOnItemClickListener(object:ProductsAdapter.onItemCLickListener{
                        override fun onItemClick(position: Int) {
                           val intent= Intent(this@FetchingActivity,AddDetailsActivity::class.java)

                            intent.putExtra("postId",productList[position].postId)
                            intent.putExtra("name",productList[position].name)
                            intent.putExtra("mobile",productList[position].mobile)
                            intent.putExtra("addTopic",productList[position].addTopic)
                            intent.putExtra("description",productList[position].description)
                            startActivity(intent)
                        }

                    })

                    prorecycle.visibility=View.VISIBLE
                    tvloadingData.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}


