package com.example.a1309_baitap

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ProductAdapter(private val context: Context, private val productList: List<Product>):
    BaseAdapter() {
    override fun getCount(): Int {
        return productList.size
    }

    override fun getItem(position: Int): Any? {
       return productList[position]
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {

        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.grid_item_product,parent,false)
        val product = productList[position]

        val productImg = view.findViewById<ImageView>(R.id.productImage)
        val productName = view.findViewById<TextView>(R.id.txtProductName)
        val productPrice = view.findViewById<TextView>(R.id.txtProductPrice)

        val uri = Uri.parse(product.image)
        productImg.setImageURI(uri)
        productName.text = product.name
        productPrice.text = product.price.toString()
        return view
    }

}