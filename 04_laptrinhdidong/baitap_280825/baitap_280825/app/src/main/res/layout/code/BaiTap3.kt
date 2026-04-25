package com.example.baitap_280825

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

class BaiTap3 : AppCompatActivity() {

    private lateinit var tvStatus: TextView
    private lateinit var etContent: EditText
    private lateinit var btnClearAll: Button
    private lateinit var btnSave: Button
    private lateinit var btnLoad: Button

    private val fileName = "test.txt"
    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.baitap3)

        tvStatus = findViewById(R.id.tv_status)
        etContent = findViewById(R.id.et_content)
        btnClearAll = findViewById(R.id.btn_clear_all)
        btnSave = findViewById(R.id.btn_save)
        btnLoad = findViewById(R.id.btn_load)

        // Kiểm tra và yêu cầu quyền khi khởi động ứng dụng
        checkPermissions()

        btnClearAll.setOnClickListener {
            etContent.setText("")
            tvStatus.text = "Android\nDemo ghi va doc file van ban ra the nho\nLuu y:\nPhai cap phep ghi trong tap tin AndroidManifest.xml"
        }

        btnSave.setOnClickListener {
            saveFile()
        }

        btnLoad.setOnClickListener {
            loadFile()
        }
    }

    /**
     * Hàm kiểm tra và yêu cầu quyền
     */
    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Quyền đã được cấp!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Ứng dụng cần quyền để hoạt động.", Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Viết một hàm tên kiemtrathenho() để kiểm tra tình trạng bộ nhớ.
     * Trả về: 0 (không đọc/ghi), 1 (chỉ đọc), 2 (đọc/ghi được)
     */
    private fun kiemtrathenho(): Int {
        val state = Environment.getExternalStorageState()
        return when {
            Environment.MEDIA_MOUNTED == state -> 2
            Environment.MEDIA_MOUNTED_READ_ONLY == state -> 1
            else -> 0
        }
    }

    /**
     * Lưu file test.txt vào External Storage
     */
    private fun saveFile() {
        val storageState = kiemtrathenho()
        if (storageState == 2) { // Có thể ghi được
            val content = etContent.text.toString()
            if (content.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập nội dung!", Toast.LENGTH_SHORT).show()
                return
            }

            var fileOutputStream: FileOutputStream? = null
            try {
                val file = File(getExternalFilesDir(null), fileName)
                fileOutputStream = FileOutputStream(file)
                fileOutputStream.write(content.toByteArray())
                tvStatus.text = "Trạng thái: Đã ghi file '$fileName' thành công."
            } catch (e: IOException) {
                e.printStackTrace()
                tvStatus.text = "Trạng thái: Lỗi khi ghi file: ${e.message}"
            } finally {
                fileOutputStream?.close()
            }
        } else {
            tvStatus.text = "Trạng thái: Bộ nhớ ngoài không có sẵn hoặc không thể ghi."
        }
    }

    /**
     * Đọc file test.txt từ External Storage
     */
    private fun loadFile() {
        val storageState = kiemtrathenho()
        if (storageState >= 1) { // Có thể đọc được
            var fileInputStream: FileInputStream? = null
            try {
                val file = File(getExternalFilesDir(null), fileName)
                if (!file.exists()) {
                    tvStatus.text = "Trạng thái: File '$fileName' không tồn tại."
                    return
                }

                fileInputStream = FileInputStream(file)
                val inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder = StringBuilder()
                var text: String?
                while (bufferedReader.readLine().also { text = it } != null) {
                    stringBuilder.append(text)
                }
                etContent.setText(stringBuilder.toString())
                tvStatus.text = "Trạng thái: Đã đọc file '$fileName' thành công."
            } catch (e: IOException) {
                e.printStackTrace()
                tvStatus.text = "Trạng thái: Lỗi khi đọc file: ${e.message}"
            } finally {
                fileInputStream?.close()
            }
        } else {
            tvStatus.text = "Trạng thái: Bộ nhớ ngoài không có sẵn."
        }
    }
}