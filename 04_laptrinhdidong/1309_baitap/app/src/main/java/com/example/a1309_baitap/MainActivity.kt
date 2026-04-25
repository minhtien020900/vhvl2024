package com.example.a1309_baitap

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var adapter: ProductAdapter
    private lateinit var listSanPham: MutableList<Product>

    private lateinit var edtTenSP: EditText
    private lateinit var edtPrice: EditText
    val PICK_IMAGE = 100
    var imageUri: Uri? = null

//    private val pickImageLauncher = registerForActivityResult(
//        androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == RESULT_OK && result.data != null) {
//            imageUri = result.data!!.data  // lấy Uri ảnh đã chọn
//            // Ví dụ: gán ảnh preview vào ImageView
//            val imgPreview = findViewById<ImageView>(R.id.imgPreview)
//            imgPreview.setImageURI(imageUri)
//        }
//    }
    private val pickMedia = registerForActivityResult(
    ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            imageUri = uri
            val imgPreview = findViewById<ImageView>(R.id.imgPreview)
            imgPreview.setImageURI(uri)
        }
    }
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
        edtTenSP = findViewById(R.id.edtTenSP)
        edtPrice = findViewById(R.id.edtPrice)

        listSanPham = dbHelper.getAllProducts().toMutableList()

        val btnSelectPicture = findViewById<Button>(R.id.chonAnh)
        val btnAdd = findViewById<Button>(R.id.btnThem)
        adapter = ProductAdapter(this,listSanPham)
        val girdView = findViewById<GridView>(R.id.gridView)
        girdView.adapter = adapter

        btnSelectPicture.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//            checkPermissionAndOpenGallery()
        }
        btnAdd.setOnClickListener {
            val prdName = edtTenSP.text.toString()
            val productPrice = edtPrice.text.toString().toIntOrNull() ?: 0


            if (dbHelper.insertSanPham(prdName,productPrice,imageUri.toString())) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
                refreshList()
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun refreshList() {
        listSanPham.clear()
        listSanPham.addAll(dbHelper.getAllProducts())
        adapter.notifyDataSetChanged()
    }
//    private fun checkPermissionAndOpenGallery() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            // Android 13+ dùng READ_MEDIA_IMAGES
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
//                != PackageManager.PERMISSION_GRANTED) {
//
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
//                    101
//                )
//            } else {
//                openGallery()
//            }
//        } else {
//            // Android 12 trở xuống dùng READ_EXTERNAL_STORAGE
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//                    101
//                )
//            } else {
//                openGallery()
//            }
//        }
//    }

//    private fun openGallery() {
//        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        pickImageLauncher.launch(galleryIntent)
//    }
}