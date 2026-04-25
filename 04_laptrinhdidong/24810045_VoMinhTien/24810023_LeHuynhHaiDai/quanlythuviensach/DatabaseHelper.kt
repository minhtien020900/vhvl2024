package com.example.quanlythuviensach

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, "Library.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE books (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                author TEXT,
                category TEXT,
                price REAL
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS books")
        onCreate(db)
    }

    fun insertBook(book: Book): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", book.title)
            put("author", book.author)
            put("category", book.category)
            put("price", book.price)
        }
        return db.insert("books", null, values)
    }

    fun updateBook(book: Book): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", book.title)
            put("author", book.author)
            put("category", book.category)
            put("price", book.price)
        }
        return db.update("books", values, "id=?", arrayOf(book.id.toString()))
    }

    fun deleteBook(id: Int): Int {
        val db = writableDatabase
        return db.delete("books", "id=?", arrayOf(id.toString()))
    }

    fun getAllBooks(): ArrayList<Book> {
        val list = ArrayList<Book>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM books", null)
        if (cursor.moveToFirst()) {
            do {
                val book = Book(
                    id = cursor.getInt(0),
                    title = cursor.getString(1),
                    author = cursor.getString(2),
                    category = cursor.getString(3),
                    price = cursor.getDouble(4)
                )
                list.add(book)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun searchBooks(keyword: String): ArrayList<Book> {
        val list = ArrayList<Book>()
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?",
            arrayOf("%$keyword%", "%$keyword%")
        )
        if (cursor.moveToFirst()) {
            do {
                val book = Book(
                    id = cursor.getInt(0),
                    title = cursor.getString(1),
                    author = cursor.getString(2),
                    category = cursor.getString(3),
                    price = cursor.getDouble(4)
                )
                list.add(book)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun filterByCategory(category: String): ArrayList<Book> {
        val list = ArrayList<Book>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM books WHERE category=?", arrayOf(category))
        if (cursor.moveToFirst()) {
            do {
                val book = Book(
                    id = cursor.getInt(0),
                    title = cursor.getString(1),
                    author = cursor.getString(2),
                    category = cursor.getString(3),
                    price = cursor.getDouble(4)
                )
                list.add(book)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun getBookCount(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM books", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        return count
    }

    fun getAllCategories(): ArrayList<String> {
        val list = ArrayList<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT DISTINCT category FROM books", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

}