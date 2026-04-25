package com.example.quanlythuviensach

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddEditBookActivity : AppCompatActivity() {

    private lateinit var edtTitle: EditText
    private lateinit var edtAuthor: EditText
    private lateinit var edtCategory: EditText
    private lateinit var edtPrice: EditText
    private lateinit var btnSave: Button

    private lateinit var dbHelper: DatabaseHelper
    private var bookId: Int = -1  // -1 = thêm mới, khác -1 = sửa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_book)

        dbHelper = DatabaseHelper(this)

        edtTitle = findViewById(R.id.edtTitle)
        edtAuthor = findViewById(R.id.edtAuthor)
        edtCategory = findViewById(R.id.edtCategory)
        edtPrice = findViewById(R.id.edtPrice)
        btnSave = findViewById(R.id.btnSave)

        // Nhận dữ liệu (nếu sửa)
        val intent = intent
        bookId = intent.getIntExtra("id", -1)
        if (bookId != -1) {
            edtTitle.setText(intent.getStringExtra("title"))
            edtAuthor.setText(intent.getStringExtra("author"))
            edtCategory.setText(intent.getStringExtra("category"))
            edtPrice.setText(intent.getDoubleExtra("price", 0.0).toString())
            btnSave.text = "Cập nhật sách"
        } else {
            btnSave.text = "Thêm sách"
        }

        btnSave.setOnClickListener {
            val title = edtTitle.text.toString().trim()
            val author = edtAuthor.text.toString().trim()
            val category = edtCategory.text.toString().trim()
            val price = edtPrice.text.toString().trim().toDoubleOrNull()

            if (title.isEmpty() || author.isEmpty() || category.isEmpty() || price == null) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (bookId == -1) {
                // Thêm mới
                val book = Book(title = title, author = author, category = category, price = price)
                dbHelper.insertBook(book)
                Toast.makeText(this, "Đã thêm sách!", Toast.LENGTH_SHORT).show()
            } else {
                // Cập nhật
                val book = Book(id = bookId, title = title, author = author, category = category, price = price)
                dbHelper.updateBook(book)
                Toast.makeText(this, "Đã cập nhật sách!", Toast.LENGTH_SHORT).show()
            }

            finish() // quay lại MainActivity
        }
    }
}