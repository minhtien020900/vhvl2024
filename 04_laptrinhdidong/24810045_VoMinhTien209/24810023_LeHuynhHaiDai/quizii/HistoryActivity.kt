package com.example.baitap_180925

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {
    private lateinit var db: QuizDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        db = QuizDBHelper(this)
        val history = db.getAllResults()
        val items = history.map { "Ngày: ${it.date}  -  Điểm: ${it.score}" }
        findViewById<ListView>(R.id.lvHistory).adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
    }
}