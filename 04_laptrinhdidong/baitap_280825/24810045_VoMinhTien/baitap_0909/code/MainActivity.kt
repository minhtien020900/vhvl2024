package com.example.baitap_tailop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    lateinit var adapter: CustomAdapter_DiemMonHoc
    val ds = ArrayList<DiemMonHoc>()
    var selectedId: Int? = null

    val dsMonHoc = listOf("Lập trình C#", "Lập trình Java", "Lập trình Kotlin")
    val dsSinhVien = listOf("Võ Miên Tính", "Lê Ninh Thuận", "Huỳnh Hải Đại")
    val dsHinh = listOf(R.drawable.csharp, R.drawable.java, R.drawable.kotlin)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = DatabaseHelper(this)

        val edtNgay = findViewById<EditText>(R.id.edtNgay)
        val edtHeSo = findViewById<EditText>(R.id.edtHeSo)
        val edtDiem = findViewById<EditText>(R.id.edtDiem)
        val spMonHoc = findViewById<Spinner>(R.id.spMonHoc)
        val spSinhVien = findViewById<Spinner>(R.id.spSinhVien)
        val lv = findViewById<ListView>(R.id.lvDanhSach)

        spMonHoc.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dsMonHoc)
        spSinhVien.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dsSinhVien)

        loadData(lv)

        findViewById<Button>(R.id.btnThem).setOnClickListener {
            val ngay = edtNgay.text.toString()
            val heSo = edtHeSo.text.toString().toIntOrNull() ?: 1
            val diem = edtDiem.text.toString().toFloatOrNull() ?: 0f

            val monHocIndex = spMonHoc.selectedItemPosition
            val monHoc = dsMonHoc[monHocIndex]
            val hinh = dsHinh[monHocIndex]   // ảnh theo môn học
            val sinhVien = spSinhVien.selectedItem.toString()

            db.insert(ngay, heSo, diem, monHoc, sinhVien, hinh)
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
            loadData(lv)
        }

        lv.setOnItemClickListener { _, _, position, _ ->
            val item = ds[position]
            selectedId = item.id
            edtNgay.setText(item.ngay)
            edtHeSo.setText(item.heSo.toString())
            edtDiem.setText(item.diem.toString())
            spMonHoc.setSelection(dsMonHoc.indexOf(item.monHoc))
            spSinhVien.setSelection(dsSinhVien.indexOf(item.sinhVien))
        }

        findViewById<Button>(R.id.btnSua).setOnClickListener {
            selectedId?.let {
                val ngay = edtNgay.text.toString()
                val heSo = edtHeSo.text.toString().toInt()
                val diem = edtDiem.text.toString().toFloat()
                val monHocIndex = spMonHoc.selectedItemPosition
                val monHoc = dsMonHoc[monHocIndex]
                val hinh = dsHinh[monHocIndex]
                val sinhVien = spSinhVien.selectedItem.toString()

                db.update(it, ngay, heSo, diem, monHoc, sinhVien, hinh)
                loadData(lv)
            }
        }

        findViewById<Button>(R.id.btnXoa).setOnClickListener {
            selectedId?.let {
                db.delete(it)
                loadData(lv)
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            db.clear()
            loadData(lv)
        }
    }

    private fun loadData(lv: ListView) {
        ds.clear()
        val cursor = db.getAll()
        while (cursor.moveToNext()) {
            ds.add(
                DiemMonHoc(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getFloat(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6)
                )
            )
        }
        cursor.close()
        adapter = CustomAdapter_DiemMonHoc(this, ds)
        lv.adapter = adapter
    }
}