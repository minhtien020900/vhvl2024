package com.example.a03_btvn_recycler_view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var adapter: ProductAdapter
    private var currentKeyword: CharSequence = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val products = mutableListOf(Product("Tiger", "23.000 VND", R.drawable.tiger),
            Product("Sài gòn xanh", "15.000 VND", R.drawable.saigonxanh),
            Product("Sài gòn bạc", "26.000 VND", R.drawable.saigonbac),
            Product("Heineken", "33.000 VND", R.drawable.heineken),
            Product("Quy nhơn", "15.000 VND", R.drawable.quynhon),
            Product("Larue", "28.000 VND", R.drawable.larue))

        val recyclerView: RecyclerView = findViewById(R.id.recylerview)
        val searchInput: EditText = findViewById(R.id.search)
        adapter = ProductAdapter(products) { product ->
            adapter.remove(product)
            adapter.filter.filter(currentKeyword)
            Toast.makeText(this, "Đã xoá ${product.name}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter= adapter
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                currentKeyword = s.toString()
                adapter.filter.filter(s)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

    }
}