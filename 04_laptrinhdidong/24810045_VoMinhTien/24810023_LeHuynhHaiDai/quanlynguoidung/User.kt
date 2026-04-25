package com.example.quanlynguoidung

data class User(
    var id: Int = 0,
    var name: String,
    var email: String,
    var phone: String,
    var avatarPath: String? = null
)