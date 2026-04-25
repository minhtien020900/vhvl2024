package com.example.baitap_180925

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class QuizDBHelper(context: Context) :
    SQLiteOpenHelper(context, "quiz.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE questions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                question TEXT,
                optionA TEXT,
                optionB TEXT,
                optionC TEXT,
                correctAnswer TEXT
            )
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE results (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                score INTEGER,
                date TEXT
            )
        """.trimIndent())

        // Seed dữ liệu câu hỏi
        seedQuestions(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS questions")
        db.execSQL("DROP TABLE IF EXISTS results")
        onCreate(db)
    }

    private fun seedQuestions(db: SQLiteDatabase) {
        val questions = listOf(
            Question(0, "Thủ đô của Việt Nam là gì?", "Hà Nội", "TP.HCM", "Đà Nẵng", "A"),
            Question(0, "2 + 3 = ?", "4", "5", "6", "B"),
            Question(0, "Ngôn ngữ chính của Android là?", "Java/Kotlin", "Swift", "C#", "A")
        )
        for (q in questions) {
            val cv = ContentValues().apply {
                put("question", q.question)
                put("optionA", q.optionA)
                put("optionB", q.optionB)
                put("optionC", q.optionC)
                put("correctAnswer", q.correctAnswer)
            }
            db.insert("questions", null, cv)
        }
    }

    fun getAllQuestions(): List<Question> {
        val list = mutableListOf<Question>()
        val c = readableDatabase.rawQuery("SELECT * FROM questions", null)
        while (c.moveToNext()) {
            list.add(
                Question(
                    c.getLong(c.getColumnIndexOrThrow("id")),
                    c.getString(c.getColumnIndexOrThrow("question")),
                    c.getString(c.getColumnIndexOrThrow("optionA")),
                    c.getString(c.getColumnIndexOrThrow("optionB")),
                    c.getString(c.getColumnIndexOrThrow("optionC")),
                    c.getString(c.getColumnIndexOrThrow("correctAnswer"))
                )
            )
        }
        c.close()
        return list
    }

    fun insertResult(score: Int, date: String) {
        val cv = ContentValues().apply {
            put("score", score)
            put("date", date)
        }
        writableDatabase.insert("results", null, cv)
    }

    fun getAllResults(): List<ResultHistory> {
        val list = mutableListOf<ResultHistory>()
        val c = readableDatabase.rawQuery("SELECT * FROM results ORDER BY date DESC", null)
        while (c.moveToNext()) {
            list.add(
                ResultHistory(
                    c.getLong(c.getColumnIndexOrThrow("id")),
                    c.getInt(c.getColumnIndexOrThrow("score")),
                    c.getString(c.getColumnIndexOrThrow("date"))
                )
            )
        }
        c.close()
        return list
    }
}