package com.example.baitap_180925

data class Question(
    val id: Long = 0,
    val question: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val correctAnswer: String
)
