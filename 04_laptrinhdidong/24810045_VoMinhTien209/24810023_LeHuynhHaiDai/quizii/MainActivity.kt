package com.example.baitap_180925

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var db: QuizDBHelper
    private lateinit var btnStartQuiz: Button
    private lateinit var btnHistory: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = QuizDBHelper(this) // tạo DB và seed dữ liệu

        btnStartQuiz = findViewById(R.id.btnStartQuiz)
        btnHistory = findViewById(R.id.btnHistory)

        btnStartQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}