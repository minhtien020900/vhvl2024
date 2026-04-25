package com.example.quanlynguoidung

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: UserDatabaseHelper
    private lateinit var adapter: UserAdapter
    private var userList: ArrayList<User> = ArrayList()

    private lateinit var recyclerView: RecyclerView
    private lateinit var edtSearch: EditText
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var imgAvatar: ImageView

    private var avatarPath: String? = null
    private val PICK_IMAGE = 100
    private var selectedUserId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = UserDatabaseHelper(this)

        recyclerView = findViewById(R.id.recyclerView)
        edtSearch = findViewById(R.id.edtSearch)
        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        imgAvatar = findViewById(R.id.imgAvatar)

        recyclerView.layoutManager = LinearLayoutManager(this)

        loadUsers()

        // chọn ảnh từ gallery
        imgAvatar.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(gallery, PICK_IMAGE)
        }

        // thêm user
        btnAdd.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val phone = edtPhone.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()) {
                dbHelper.insertUser(User(name = name, email = email, phone = phone, avatarPath = avatarPath))
                clearInput()
                loadUsers()
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show()
            }
        }

        // cập nhật user
        btnUpdate.setOnClickListener {
            if (selectedUserId != -1) {
                val name = edtName.text.toString()
                val email = edtEmail.text.toString()
                val phone = edtPhone.text.toString()
                dbHelper.updateUser(User(id = selectedUserId, name = name, email = email, phone = phone, avatarPath = avatarPath))
                clearInput()
                loadUsers()
                selectedUserId = -1
            } else {
                Toast.makeText(this, "Chưa chọn người dùng để sửa!", Toast.LENGTH_SHORT).show()
            }
        }

        // tìm kiếm
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchUsers(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    // load toàn bộ user
    private fun loadUsers() {
        userList = dbHelper.getAllUsers()
        adapter = UserAdapter(
            userList,
            onEditClick = { user ->
                selectedUserId = user.id
                edtName.setText(user.name)
                edtEmail.setText(user.email)
                edtPhone.setText(user.phone)
                avatarPath = user.avatarPath
                if (!user.avatarPath.isNullOrEmpty()) {
                    val file = File(user.avatarPath!!)
                    if (file.exists()) {
                        imgAvatar.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
                    }
                } else {
                    imgAvatar.setImageResource(R.drawable.ic_launcher_foreground)
                }
            },
            onDeleteClick = { user ->
                AlertDialog.Builder(this)
                    .setTitle("Xóa người dùng")
                    .setMessage("Bạn có chắc muốn xóa ${user.name}?")
                    .setPositiveButton("Xóa") { _: DialogInterface, _: Int ->
                        dbHelper.deleteUser(user.id)
                        loadUsers()
                    }
                    .setNegativeButton("Hủy", null)
                    .show()
            }
        )
        recyclerView.adapter = adapter
    }

    // tìm kiếm user
    private fun searchUsers(keyword: String) {
        userList = if (keyword.isEmpty()) {
            dbHelper.getAllUsers()
        } else {
            dbHelper.searchUsers(keyword)
        }
        adapter = UserAdapter(
            userList,
            onEditClick = { user ->
                selectedUserId = user.id
                edtName.setText(user.name)
                edtEmail.setText(user.email)
                edtPhone.setText(user.phone)
                avatarPath = user.avatarPath
                if (!user.avatarPath.isNullOrEmpty()) {
                    val file = File(user.avatarPath!!)
                    if (file.exists()) {
                        imgAvatar.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
                    }
                } else {
                    imgAvatar.setImageResource(R.drawable.ic_launcher_foreground)
                }
            },
            onDeleteClick = { user ->
                dbHelper.deleteUser(user.id)
                loadUsers()
            }
        )
        recyclerView.adapter = adapter
    }

    // clear form
    private fun clearInput() {
        edtName.text.clear()
        edtEmail.text.clear()
        edtPhone.text.clear()
        imgAvatar.setImageResource(R.drawable.ic_launcher_foreground)
        avatarPath = null
    }

    // nhận ảnh từ gallery
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            val uri = data.data
            if (uri != null) {
                val projection = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = contentResolver.query(uri, projection, null, null, null)
                cursor?.let {
                    val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    cursor.moveToFirst()
                    avatarPath = cursor.getString(columnIndex)
                    cursor.close()
                }
                imgAvatar.setImageURI(uri)
            }
        }
    }
}