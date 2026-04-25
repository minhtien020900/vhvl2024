package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//
        val productSpinner: Spinner = findViewById(R.id.productSpinner)
        val btnOrder: Button = findViewById(R.id.order)

        val productList = listOf(
            Product("Phở","55.000đ", imageResId = R.drawable.pho),
            Product("Cơm tấm","46.000đ", imageResId = R.drawable.comtam),
            Product("Bún bò","35.000đ", imageResId = R.drawable.bunbo),

        )
        val adapter = ProductAdapter(this,productList)
        productSpinner.adapter = adapter

        val inputQuantity: EditText = findViewById(R.id.quantity)
//        productSpinner.setOnItemClickListener {
//           _,_, position,  _->
//            val selectedProduct = productList[position]
//            Toast.makeText(this,"Bạn chọn: ${selectedProduct.name} - Màu: ${selectedProduct.color}",Toast.LENGTH_SHORT).show()
//        }
        btnOrder.setOnClickListener {
            val positionSelected = productSpinner.selectedItemPosition
            val productSelected = productList[positionSelected]
            val quantity  = inputQuantity.text.toString()
            Toast.makeText(this,"Bạn đã đặt hàng $quantity ${productSelected.name}",Toast.LENGTH_SHORT).show()

        }
        productSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
//                selectedProduct = productList[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")
            }
        }
    }
}