package com.example.a24810045_vominhtien

import android.content.Intent
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

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listSanPham: MutableList<SanPham>

    private lateinit var edtMaSP: EditText
    private lateinit var edtTenSP: EditText
    private lateinit var edtSoLuong: EditText
    private lateinit var lvSanPham: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = DBHelper(this)

        edtMaSP = findViewById(R.id.edtMaSP)
        edtTenSP = findViewById(R.id.edtTenSP)
        edtSoLuong = findViewById(R.id.edtSoLuong)
        lvSanPham = findViewById(R.id.lvSanPham)

        listSanPham = dbHelper.getAllSanpham().toMutableList()
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listSanPham.map { sp -> "${sp.masp} ${sp.ten} - ${sp.soluong} sinh viên" })
        lvSanPham.adapter = adapter

        lvSanPham.setOnItemClickListener { _, _, position, _ ->
            val sp = listSanPham[position]
            edtMaSP.setText(sp.masp)
            edtTenSP.setText(sp.ten)
            edtSoLuong.setText(sp.soluong.toString())
        }

        findViewById<Button>(R.id.btnThem).setOnClickListener {
            val masp = edtMaSP.text.toString()
            val ten = edtTenSP.text.toString()
            val soluong = edtSoLuong.text.toString().toIntOrNull() ?: 0

            if (dbHelper.insertSanPham(masp, ten, soluong)) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
                refreshList()
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.btnThem).setOnClickListener {
            val masp = edtMaSP.text.toString()
            val ten = edtTenSP.text.toString()
            val soluong = edtSoLuong.text.toString().toIntOrNull() ?: 0

            if (dbHelper.insertSanPham(masp, ten, soluong)) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
                refreshList()
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.btnCapNhat).setOnClickListener {
            val masp = edtMaSP.text.toString()
            val ten = edtTenSP.text.toString()
            val soluong = edtSoLuong.text.toString().toIntOrNull() ?: 0

            if (dbHelper.updateSanPham(masp, ten, soluong)) {
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                refreshList()
            } else {
                Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.btnXoa).setOnClickListener {
            val masp = edtMaSP.text.toString()

            if (dbHelper.deleteSanPham(masp)) {
                Toast.makeText(this, "Xoá thành công", Toast.LENGTH_SHORT).show()
                refreshList()
            } else {
                Toast.makeText(this, "Xoá thất bại", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.btnHienThi).setOnClickListener {
            val intent = Intent(this, ViewListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun refreshList() {
        listSanPham.clear()
        listSanPham.addAll(dbHelper.getAllSanpham())
        adapter.clear()
        adapter.addAll(listSanPham.map { sp -> "${sp.masp} ${sp.ten} - ${sp.soluong} sinh viên" })
        adapter.notifyDataSetChanged()
    }
}