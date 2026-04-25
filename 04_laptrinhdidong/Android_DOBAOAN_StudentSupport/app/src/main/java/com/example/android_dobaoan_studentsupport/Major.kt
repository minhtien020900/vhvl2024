package com.example.android_dobaoan_studentsupport

data class Major(
    val id: Int,
    val code: String,
    val name: String,
    val admissionBlock: String, // Khối xét tuyển (A00, B00, ...)
    val benchmarkScore: Double, // Điểm chuẩn
    val universityCode: String // Khóa ngoại liên kết với trường
)