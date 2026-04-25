package com.example.a24810045_vominhtien

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ViewListActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var lvSanPham: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listSanPham: MutableList<SanPham>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dbHelper = DBHelper(this)
        lvSanPham = findViewById(R.id.lvSanPham2)

        listSanPham = dbHelper.getAllSanpham().toMutableList()
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listSanPham.map { sp -> "${sp.masp} ${sp.ten} - ${sp.soluong} sinh viên" }
        )
        lvSanPham.adapter = adapter
    }
}