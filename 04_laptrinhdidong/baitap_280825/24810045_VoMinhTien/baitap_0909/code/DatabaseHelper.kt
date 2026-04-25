package com.example.baitap_tailop

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "QLSV.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """
            CREATE TABLE DiemMonHoc (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                ngay TEXT,
                heSo INTEGER,
                diem REAL,
                monHoc TEXT,
                sinhVien TEXT,
                hinhAnh INTEGER
            )
        """.trimIndent()
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS DiemMonHoc")
        onCreate(db)
    }

    fun insert(ngay: String, heSo: Int, diem: Float, monHoc: String, sinhVien: String, hinhAnh: Int): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("ngay", ngay)
            put("heSo", heSo)
            put("diem", diem)
            put("monHoc", monHoc)
            put("sinhVien", sinhVien)
            put("hinhAnh", hinhAnh)
        }
        return db.insert("DiemMonHoc", null, values)
    }

    fun getAll(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM DiemMonHoc", null)
    }

    fun update(id: Int, ngay: String, heSo: Int, diem: Float, monHoc: String, sinhVien: String, hinhAnh: Int): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("ngay", ngay)
            put("heSo", heSo)
            put("diem", diem)
            put("monHoc", monHoc)
            put("sinhVien", sinhVien)
            put("hinhAnh", hinhAnh)
        }
        return db.update("DiemMonHoc", values, "id=?", arrayOf(id.toString()))
    }

    fun delete(id: Int): Int {
        val db = writableDatabase
        return db.delete("DiemMonHoc", "id=?", arrayOf(id.toString()))
    }

    fun clear(): Int {
        val db = writableDatabase
        return db.delete("DiemMonHoc", null, null)
    }
}