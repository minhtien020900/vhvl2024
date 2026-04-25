package com.example.baitap_280825

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

class BaiTap2: AppCompatActivity() {
    private lateinit var tvCounter: TextView
    private lateinit var tvQuote: TextView
    private lateinit var mainLayout: View

    private var quotesList: List<String> = emptyList()
    private var currentQuoteIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.baitap2)

        tvCounter = findViewById(R.id.tv_counter)
        tvQuote = findViewById(R.id.tv_quote)
        mainLayout = findViewById(R.id.main_layout)

        loadQuotesFromFile()

        mainLayout.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                displayRandomQuote()
            }
            true
        }
    }
    private fun loadQuotesFromFile() {
        try {
            val inputStream = assets.open("danhngon.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))
            quotesList = reader.readLines().filter { it.isNotBlank() }
            reader.close()

            tvCounter.text = "0/${quotesList.size}"
        } catch (e: Exception) {
            e.printStackTrace()
            tvQuote.text = "Lỗi khi đọc file danh ngôn."
        }
    }

    private fun displayRandomQuote() {
        if (quotesList.isEmpty()) {
            tvQuote.text = "Không có danh ngôn nào để hiển thị."
            return
        }

        val randomIndex = Random.nextInt(quotesList.size)
        currentQuoteIndex = randomIndex

        tvQuote.text = quotesList[currentQuoteIndex]
        tvCounter.text = "${currentQuoteIndex + 1}/${quotesList.size}"
    }
}