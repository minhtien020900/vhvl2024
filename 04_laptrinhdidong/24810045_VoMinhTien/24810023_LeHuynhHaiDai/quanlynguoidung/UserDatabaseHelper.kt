package com.example.quanlynguoidung

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "UserDB.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql = """
        CREATE TABLE users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT,
            email TEXT,
            phone TEXT,
            avatarPath TEXT
        )
    """
        db.execSQL(sql)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun insertUser(user: User): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", user.name)
            put("email", user.email)
            put("phone", user.phone)
        }
        return db.insert("users", null, values)
    }

    fun updateUser(user: User): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", user.name)
            put("email", user.email)
            put("phone", user.phone)
        }
        return db.update("users", values, "id=?", arrayOf(user.id.toString()))
    }

    fun deleteUser(id: Int): Int {
        val db = writableDatabase
        return db.delete("users", "id=?", arrayOf(id.toString()))
    }

    fun getAllUsers(): ArrayList<User> {
        val list = ArrayList<User>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun searchUsers(keyword: String): ArrayList<User> {
        val list = ArrayList<User>()
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM users WHERE name LIKE ? OR email LIKE ?",
            arrayOf("%$keyword%", "%$keyword%")
        )
        if (cursor.moveToFirst()) {
            do {
                list.add(User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}