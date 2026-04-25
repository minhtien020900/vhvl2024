package com.example.quanlythuviensach

import android.view.View
import android.widget.AdapterView
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var edtSearch: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: BookAdapter
    private var bookList: ArrayList<Book> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)

        edtSearch = findViewById(R.id.edtSearch)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadBooks()

        // Khởi tạo Spinner (thể loại)
        val categories = arrayListOf("Tất cả", "Văn học", "Khoa học", "Kinh tế", "Thiếu nhi")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = spinnerAdapter

        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                filterBooks()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // không cần xử lý
            }
        }


        // Tìm kiếm
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchBooks(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Nút thêm sách
        val fabAdd: FloatingActionButton = findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {
            startActivity(Intent(this, AddEditBookActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadBooks()
        loadCategories()
    }


    private fun loadBooks() {
        bookList = dbHelper.getAllBooks()
        adapter = BookAdapter(bookList)
        recyclerView.adapter = adapter
        Toast.makeText(this, "Tổng số sách: ${dbHelper.getBookCount()}", Toast.LENGTH_SHORT).show()
    }

    private fun searchBooks(keyword: String) {
        if (keyword.isEmpty()) {
            loadBooks()
        } else {
            bookList = dbHelper.searchBooks(keyword)
            adapter = BookAdapter(bookList)
            recyclerView.adapter = adapter
        }
    }

    private fun filterBooks() {
        val category = spinnerCategory.selectedItem.toString()
        if (category == "Tất cả") {
            loadBooks()
        } else {
            bookList = dbHelper.filterByCategory(category)
            adapter = BookAdapter(bookList)
            recyclerView.adapter = adapter
        }
    }

    private fun loadCategories() {
        val categories = dbHelper.getAllCategories()
        categories.add(0, "Tất cả") // luôn có mục "Tất cả" ở đầu

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = spinnerAdapter
    }

}