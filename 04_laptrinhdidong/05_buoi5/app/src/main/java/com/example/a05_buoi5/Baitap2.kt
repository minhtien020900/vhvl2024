package com.example.a05_buoi5

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random
class Baitap2 : AppCompatActivity() {
    private lateinit var rootLayout: LinearLayout
    private lateinit var txtCounter: TextView
    private lateinit var txtQuote: TextView
    private var quotes: List<String> = emptyList()
    private var currentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_baitap2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        quotes = readQuotesFromRaw()
        rootLayout = findViewById(R.id.main)
        txtCounter = findViewById(R.id.txtCounter)
        txtQuote = findViewById(R.id.txtQuote)

        rootLayout.setOnClickListener {
            if (quotes.isNotEmpty()) {
                val index = Random.nextInt(quotes.size)
                txtCounter.text = "${index + 1} / ${quotes.size}"
                txtQuote.text = quotes[index]
            }
        }
    }
    private fun readQuotesFromRaw(): List<String> {
        val inputStream = resources.openRawResource(R.raw.danhngon)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val list = mutableListOf<String>()
        reader.forEachLine { line ->
            val trimmed = line.trim()
            if (trimmed.isNotEmpty()) {
                list.add(trimmed)
            }
        }
        reader.close()
        return list
    }

}