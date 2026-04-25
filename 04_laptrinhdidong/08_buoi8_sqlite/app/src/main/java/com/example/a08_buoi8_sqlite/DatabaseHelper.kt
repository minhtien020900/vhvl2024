package com.example.a08_buoi8_sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "StudentDB"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Students"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME(
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_AGE INTEGER,
            $COLUMN_NAME TEXT
            
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addStudent(name: String, age: Int): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_AGE, age)
        }
        return db.insert(TABLE_NAME, null, values)


    }

    fun deleteStudent(id: Int): Int {
        val db = writableDatabase

        return db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id.toString()))

    }

    fun updateStudent(id: Int, name: String, age: Int): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_AGE, age)
        }
        return db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf(id.toString()))

    }

    fun searchStudent(query: String): Cursor {
        val db = readableDatabase

        return db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME LIKE ?",
            arrayOf("%$query%")
        )
    }fun getAllStudents(): Cursor {
        val db = readableDatabase

        return db.rawQuery(
            "SELECT * FROM $TABLE_NAME",
            null
        )
    }
}