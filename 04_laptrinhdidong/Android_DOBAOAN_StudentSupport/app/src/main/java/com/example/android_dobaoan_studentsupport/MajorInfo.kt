package com.example.android_dobaoan_studentsupport

data class MajorInfo(
    val majorId: Int,
    val majorCode: String,
    val majorName: String,
    val universityName: String,
    val admissionBlock: String,
    val benchmarkScore: Double,
    var isFavorite: Boolean = false
)