package com.example.tesing.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tesing.R
import com.example.tesing.models.ProductsModel

class ProductsAdapter(private val ProductList:ArrayList<ProductsModel>)
    :RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private lateinit var mListener:onItemCLickListener

interface onItemCLickListener{
    fun onItemClick(position:Int)
}

fun setOnItemClickListener(clickListener: onItemCLickListener){
    mListener=clickListener
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.fetch_topic,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
val currentTopic=ProductList[position]
        holder.tvTopic.text=currentTopic.addTopic
    }

    override fun getItemCount(): Int {
        return ProductList.size
    }
    class ViewHolder(itemView: View,clickListener:onItemCLickListener): RecyclerView.ViewHolder(itemView) {
        val tvTopic: TextView= itemView.findViewById(R.id.tvTopic)

        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}
