package com.example.baitap_280825

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var btnSave: Button
    private lateinit var btnLoad: Button

    private val filename = "myfile.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Ánh xạ các view từ layout
        editText = findViewById(R.id.editText)
        btnSave = findViewById(R.id.btn_save)

        // 2. Xử lý sự kiện khi nhấn nút Save
        btnSave.setOnClickListener {
            val fileContents = editText.text.toString()
            if (fileContents.isNotEmpty()) {
                saveFile(fileContents)
            } else {
                Toast.makeText(applicationContext, "Vui lòng nhập nội dung!", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Xử lý sự kiện khi nhấn nút Load
        btnLoad.setOnClickListener {
            loadFile()
        }
    }

    private fun saveFile(content: String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            fileOutputStream.write(content.toByteArray())
            Toast.makeText(applicationContext, "Lưu file thành công!", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "Lỗi khi lưu file: ${e.message}", Toast.LENGTH_LONG).show()
        } finally {
            fileOutputStream?.close()
        }
    }

    private fun loadFile() {
        var fileInputStream: FileInputStream? = null
        try {
            val file = File(filesDir, filename)
            if (!file.exists()) {
                Toast.makeText(applicationContext, "Không tìm thấy file!", Toast.LENGTH_SHORT).show()
                return
            }

            fileInputStream = openFileInput(filename)
            val fileContents = fileInputStream.bufferedReader().use { it.readText() }
            editText.setText(fileContents)
            Toast.makeText(applicationContext, "Đọc file thành công!", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "Lỗi khi đọc file: ${e.message}", Toast.LENGTH_LONG).show()
        } finally {
            fileInputStream?.close()
        }
    }
}