package com.example.a09_baitapquanlycv


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.apply

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "TaskDB"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Tasks"
        const val TABLE_PRODUCT_NAME = "Products"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESC = "desc"
        const val COLUMN_STATUS = "status"

    }

    //    override fun onCreate(db: SQLiteDatabase) {
////        val createTableQuery = """
////            CREATE TABLE $TABLE_NAME(
////            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
////            $COLUMN_AGE INTEGER,
////            $COLUMN_NAME TEXT
////
////            )
////        """.trimIndent()
////        db.execSQL(createTableQuery)
//
//    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Tasks (id INTEGER PRIMARY KEY, name TEXT, 'desc' TEXT, 'status' INTEGER)")
//        db.execSQL("CREATE TABLE Cart (id INTEGER PRIMARY KEY AUTOINCREMENT, productId INTEGER)")
//        db.execSQL("INSERT INTO Products (name, price, imageResId) VALUES ('Sản phẩm A', 10000, ${R.drawable.tiger})")
//        db.execSQL("INSERT INTO Products (name, price, imageResId) VALUES ('Sản phẩm B', 9000, ${R.drawable.saigonxanh})")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
//        onCreate(db)
    }

    fun getAllTask(): List<TaskModel> {
        val db = readableDatabase
        val list = mutableListOf<TaskModel>()
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME",
            null
        )
        while (cursor.moveToNext()) {
            list.add(
                TaskModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
                ))
        }
        cursor.close()
        return list
    }

    fun addTask(name: String, desc: String,status:Int): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_DESC, desc)
            put(COLUMN_STATUS, status)
        }
        return db.insert(TABLE_NAME, null, values)


    }

    fun deleteStudent(id: Int): Int {
        val db = writableDatabase

        return db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id.toString()))

    }

//    fun updateStudent(id: Int, name: String, age: Int): Int {
//        val db = writableDatabase
//        val values = ContentValues().apply {
//            put(COLUMN_NAME, name)
//            put(COLUMN_AGE, age)
//        }
//        return db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf(id.toString()))
//
//    }

    fun searchStudent(query: String): Cursor {
        val db = readableDatabase

        return db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME LIKE ?",
            arrayOf("%$query%")
        )
    }

    fun getAllStudents(): Cursor {
        val db = readableDatabase

        return db.rawQuery(
            "SELECT * FROM $TABLE_NAME",
            null
        )
    }
}