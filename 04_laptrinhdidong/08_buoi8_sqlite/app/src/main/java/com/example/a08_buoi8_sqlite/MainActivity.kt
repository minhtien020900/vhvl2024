package com.example.a08_buoi8_sqlite

import android.app.Activity
import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.a08_buoi8_sqlite.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = DatabaseHelper(this)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val ageEditText = findViewById<EditText>(R.id.ageEditText)
        val addButton = findViewById<Button>(R.id.addButton)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val listView = findViewById<ListView>(R.id.listView)
//        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
//
//        val product = Product(masp = "SP01", tensp = "Laptop Dell XPS 15")
//
//        binding.product=product

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString().toInt()
            dbHelper.addStudent(name, age)
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
            loadAllStudents(listView)

        }
        searchButton.setOnClickListener {
            val query = nameEditText.text.toString()
            val cursor = dbHelper.searchStudent(query)
            loadStudentsFromCursor(cursor,listView)

        }


    }

    private fun loadAllStudents(listView: ListView) {
        val cursor = dbHelper.getAllStudents()
        loadStudentsFromCursor(cursor,listView)
    }
    private fun loadStudentsFromCursor(cursor: Cursor,listView: ListView){
       val students = mutableListOf<String>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow((DatabaseHelper.COLUMN_ID)))
            val name = cursor.getInt(cursor.getColumnIndexOrThrow((DatabaseHelper.COLUMN_NAME)))
            val age = cursor.getInt(cursor.getColumnIndexOrThrow((DatabaseHelper.COLUMN_AGE)))
            students.add("Mã ${id} | Tên: ${name} | Tuổi: ${age}")
        }
        cursor.close()
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,students)
        listView.adapter = adapter
    }
}