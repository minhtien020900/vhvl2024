package com.example.baitap_280825

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

class BaiTap4 : AppCompatActivity() {
    private lateinit var tvDisplayContent: TextView
    private lateinit var etInput: EditText
    private lateinit var btnClear: Button
    private lateinit var btnWrite: Button
    private lateinit var btnLoad: Button

    private val fileName = "dulieu.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.baitap4)

        tvDisplayContent = findViewById(R.id.tv_display_content)
        etInput = findViewById(R.id.et_input)
        btnClear = findViewById(R.id.btn_clear)
        btnWrite = findViewById(R.id.btn_write)
        btnLoad = findViewById(R.id.btn_load)

        btnClear.setOnClickListener {
            etInput.setText("")
            tvDisplayContent.text = "Nội dung file sẽ hiển thị tại đây."
            Toast.makeText(this, "Đã xóa sạch nội dung.", Toast.LENGTH_SHORT).show()
        }

        btnWrite.setOnClickListener {
            val contentToWrite = etInput.text.toString()
            if (contentToWrite.isNotEmpty()) {
                writeFile(contentToWrite)
            } else {
                Toast.makeText(this, "Vui lòng nhập nội dung để ghi!", Toast.LENGTH_SHORT).show()
            }
        }

        btnLoad.setOnClickListener {
            readFile()
        }
    }

    private fun writeFile(content: String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
            val modifiedContent = content.replace("\n", "[NEWLINE]")
            fileOutputStream.write(modifiedContent.toByteArray())
            Toast.makeText(this, "Đã ghi file thành công!", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Lỗi khi ghi file: ${e.message}", Toast.LENGTH_LONG).show()
        } finally {
            fileOutputStream?.close()
        }
    }

    private fun readFile() {
        var fileInputStream: FileInputStream? = null
        try {
            val file = File(filesDir, fileName)
            if (!file.exists()) {
                tvDisplayContent.text = "File không tồn tại."
                Toast.makeText(this, "Không tìm thấy file để đọc.", Toast.LENGTH_SHORT).show()
                return
            }

            fileInputStream = openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var text: String?
            while (bufferedReader.readLine().also { text = it } != null) {
                stringBuilder.append(text)
            }
            val fileContent = stringBuilder.toString()
            val formattedContent = fileContent.replace("[NEWLINE]", "\n")
            tvDisplayContent.text = formattedContent
            Toast.makeText(this, "Đã đọc file thành công!", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            tvDisplayContent.text = "Lỗi khi đọc file."
            Toast.makeText(this, "Lỗi khi đọc file: ${e.message}", Toast.LENGTH_LONG).show()
        } finally {
            fileInputStream?.close()
        }
    }
}