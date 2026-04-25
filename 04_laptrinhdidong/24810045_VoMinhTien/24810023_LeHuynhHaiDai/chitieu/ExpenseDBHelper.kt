package com.example.baitap_180925

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ExpenseDBHelper(context: Context) :
    SQLiteOpenHelper(context, "expenses.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE expenses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                amount REAL,
                datetime TEXT
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS expenses")
        onCreate(db)
    }

    fun addExpense(expense: Expense): Long {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put("title", expense.title)
            put("amount", expense.amount)
            put("datetime", expense.datetime)
        }
        return db.insert("expenses", null, cv)
    }

    private fun cursorToList(c: Cursor): List<Expense> {
        val list = mutableListOf<Expense>()
        while (c.moveToNext()) {
            val e = Expense(
                id = c.getLong(c.getColumnIndexOrThrow("id")),
                title = c.getString(c.getColumnIndexOrThrow("title")),
                amount = c.getDouble(c.getColumnIndexOrThrow("amount")),
                datetime = c.getString(c.getColumnIndexOrThrow("datetime"))
            )
            list.add(e)
        }
        c.close()
        return list
    }

    fun getAllExpenses(): List<Expense> {
        val c = readableDatabase.rawQuery("SELECT * FROM expenses ORDER BY datetime DESC", null)
        return cursorToList(c)
    }

    fun getExpensesByDate(date: String): List<Expense> {
        val c = readableDatabase.rawQuery(
            "SELECT * FROM expenses WHERE datetime LIKE ? ORDER BY datetime DESC",
            arrayOf("$date%")
        )
        return cursorToList(c)
    }

    fun getExpensesByRange(startDatetime: String, endDatetime: String): List<Expense> {
        val c = readableDatabase.rawQuery(
            "SELECT * FROM expenses WHERE datetime BETWEEN ? AND ? ORDER BY datetime DESC",
            arrayOf(startDatetime, endDatetime)
        )
        return cursorToList(c)
    }

    fun getTotalByDate(date: String): Double {
        val c = readableDatabase.rawQuery(
            "SELECT SUM(amount) FROM expenses WHERE datetime LIKE ?",
            arrayOf("$date%")
        )
        var total = 0.0
        if (c.moveToFirst()) total = c.getDouble(0)
        c.close()
        return total
    }

    fun getTotalByRange(startDatetime: String, endDatetime: String): Double {
        val c = readableDatabase.rawQuery(
            "SELECT SUM(amount) FROM expenses WHERE datetime BETWEEN ? AND ?",
            arrayOf(startDatetime, endDatetime)
        )
        var total = 0.0
        if (c.moveToFirst()) total = c.getDouble(0)
        c.close()
        return total
    }
}