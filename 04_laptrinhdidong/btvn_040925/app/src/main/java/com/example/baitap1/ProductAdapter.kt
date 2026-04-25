package com.example.baitap1

import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: List<Product>,private val activity: MainActivity):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
     inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val imgProduct: ImageView = itemView.findViewById(R.id.img)
        val name: TextView = itemView.findViewById(R.id.name)
        val price: TextView = itemView.findViewById(R.id.price)
        val btnAddCart: Button = itemView.findViewById(R.id.addCart)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ViewHolder {
        val view = android.view.LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val product = productList[position]
        holder.name.text =product.name
        holder.price.text ="${product.price} VNĐ"
        holder.imgProduct.setImageResource(product.imageResId)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}