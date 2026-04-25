package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.layout.Layout

//class ProductAdapter(private val context: Context, private val productList: List<Product>) :
//    BaseAdapter() {
//    override fun getCount(): Int = productList.size
//
//    override fun getItem(position: Int): Any = productList[position]
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
//        val view = convertView ?: LayoutInflater.from(context)
//            .inflate(R.layout.activity_item_product_spinner, parent, false)
//        val productName: TextView = view.findViewById(R.id.productName)
//        val productColor : TextView = view.findViewById(R.id.productColor)
//
//        val product = productList[position]
//        productName.text=product.name
//        productColor.text="Color ${product.color}"
//
//        return view
//    }
//}

class ProductAdapter(private val context: Context, private val productList: List<Product>) :
    ArrayAdapter<Product>(context, 0, productList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = TextView(context)
        textView.text = productList[position].name
        return textView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View? {
        return createView(position, convertView, parent)
    }
    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.activity_item_product_spinner, parent, false)

        val product = productList[position]
        val productImg = view.findViewById<ImageView>(R.id.productImage)
        val productName = view.findViewById<TextView>(R.id.productName)
        val productPrice = view.findViewById<TextView>(R.id.productPrice)

        productImg.setImageResource(product.imageResId)
        productName.text=product.name
        productPrice.text=product.price
        return view
    }

}