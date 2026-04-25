package com.example.baitap_180925

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class QuizActivity : AppCompatActivity() {

    private lateinit var db: QuizDBHelper
    private lateinit var questions: List<Question>
    private var currentIndex = 0
    private var score = 0

    private lateinit var tvQuestion: TextView
    private lateinit var rbA: RadioButton
    private lateinit var rbB: RadioButton
    private lateinit var rbC: RadioButton
    private lateinit var rgOptions: RadioGroup
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        db = QuizDBHelper(this)
        try {
            questions = db.getAllQuestions()
            if (questions.isEmpty()) {
                Toast.makeText(this, "Không có câu hỏi trong database!", Toast.LENGTH_LONG).show()
                finish()
                return
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Lỗi đọc DB: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        tvQuestion = findViewById(R.id.tvQuestion)
        rgOptions = findViewById(R.id.rgOptions)
        rbA = findViewById(R.id.rbA)
        rbB = findViewById(R.id.rbB)
        rbC = findViewById(R.id.rbC)
        btnNext = findViewById(R.id.btnNext)

        loadQuestion()

        btnNext.setOnClickListener {
            checkAnswer()
            currentIndex++
            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                saveResult()
                Toast.makeText(this, "Hoàn thành! Điểm: $score/${questions.size}", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun loadQuestion() {
        val q = questions[currentIndex]
        tvQuestion.text = "${currentIndex + 1}. ${q.question}"
        rbA.text = "A. ${q.optionA}"
        rbB.text = "B. ${q.optionB}"
        rbC.text = "C. ${q.optionC}"
        rgOptions.clearCheck()
    }

    private fun checkAnswer() {
        val selected = when (rgOptions.checkedRadioButtonId) {
            R.id.rbA -> "A"
            R.id.rbB -> "B"
            R.id.rbC -> "C"
            else -> ""
        }
        if (selected == questions[currentIndex].correctAnswer) score++
    }

    private fun saveResult() {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val date = sdf.format(Date())
        db.insertResult(score, date)
    }
}