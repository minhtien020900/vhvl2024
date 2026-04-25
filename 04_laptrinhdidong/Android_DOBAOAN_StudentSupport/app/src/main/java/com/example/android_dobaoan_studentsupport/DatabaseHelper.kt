package com.example.android_dobaoan_studentsupport

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UniversityGuide.db"
        private const val DATABASE_VERSION = 1

        // Bảng trường ĐH
        private const val TBL_UNI = "universities"
        private const val UNI_CODE = "code"
        private const val UNI_NAME = "name"
        private const val UNI_ADDRESS = "address"
        private const val UNI_PHONE = "phone"
        private const val UNI_WEBSITE = "website"

        // Bảng ngành học
        private const val TBL_MAJOR = "majors"
        private const val MAJOR_ID = "id"
        private const val MAJOR_CODE = "code"
        private const val MAJOR_NAME = "name"
        private const val MAJOR_BLOCK = "admissionBlock"
        private const val MAJOR_SCORE = "benchmarkScore"
        private const val MAJOR_UNI_CODE = "universityCode"

        // Bảng yêu thích
        private const val TBL_FAVORITE = "favorites"
        private const val FAV_ID = "id"
        private const val FAV_MAJOR_ID = "majorId"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createUniTable = "CREATE TABLE $TBL_UNI (" +
                "$UNI_CODE TEXT PRIMARY KEY," +
                "$UNI_NAME TEXT," +
                "$UNI_ADDRESS TEXT," +
                "$UNI_PHONE TEXT," +
                "$UNI_WEBSITE TEXT)"
        db?.execSQL(createUniTable)

        val createMajorTable = "CREATE TABLE $TBL_MAJOR (" +
                "$MAJOR_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$MAJOR_CODE TEXT," +
                "$MAJOR_NAME TEXT," +
                "$MAJOR_BLOCK TEXT," +
                "$MAJOR_SCORE REAL," +
                "$MAJOR_UNI_CODE TEXT," +
                "FOREIGN KEY($MAJOR_UNI_CODE) REFERENCES $TBL_UNI($UNI_CODE))"
        db?.execSQL(createMajorTable)

        val createFavoriteTable = "CREATE TABLE $TBL_FAVORITE (" +
                "$FAV_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$FAV_MAJOR_ID INTEGER UNIQUE," +
                "FOREIGN KEY($FAV_MAJOR_ID) REFERENCES $TBL_MAJOR($MAJOR_ID))"
        db?.execSQL(createFavoriteTable)

        addInitialData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TBL_FAVORITE")
        db?.execSQL("DROP TABLE IF EXISTS $TBL_MAJOR")
        db?.execSQL("DROP TABLE IF EXISTS $TBL_UNI")
        onCreate(db)
    }

    // ------------------- DỮ LIỆU MẪU -------------------
    private fun addInitialData(db: SQLiteDatabase?) {
        // Thêm trường
        val universities = listOf(
            University("BKA", "Đại học Bách khoa Hà Nội",
                "1 Đại Cồ Việt, Hai Bà Trưng, Hà Nội",
                "02438694242", "https://www.hust.edu.vn"),
            University("UIT", "Đại học Công nghệ Thông tin - ĐHQG TP.HCM",
                "Khu phố 6, P.Linh Trung, TP.Thủ Đức, TP.HCM",
                "02837252002", "https://www.uit.edu.vn"),
            University("UEH", "Đại học Kinh tế TP.HCM",
                "59C Nguyễn Đình Chiểu, P.6, Q.3, TP.HCM",
                "02838295299", "https://www.ueh.edu.vn")
        )
        universities.forEach { uni ->
            val cv = ContentValues().apply {
                put(UNI_CODE, uni.code)
                put(UNI_NAME, uni.name)
                put(UNI_ADDRESS, uni.address)
                put(UNI_PHONE, uni.phone)
                put(UNI_WEBSITE, uni.website)
            }
            db?.insert(TBL_UNI, null, cv)
        }

        // Thêm ngành
        val majors = listOf(
            Major(0, "IT1", "Khoa học Máy tính", "A00, A01", 28.5, "BKA"),
            Major(0, "EE2", "Kỹ thuật Điều khiển và Tự động hóa", "A00, A01", 27.5, "BKA"),
            Major(0, "7480201", "Công nghệ Thông tin", "A00, A01, D01", 27.9, "UIT"),
            Major(0, "7480104", "Hệ thống Thông tin", "A00, A01, D07", 26.8, "UIT"),
            Major(0, "7340301", "Kế toán", "A00, A01, D01, D07", 26.2, "UEH"),
            Major(0, "7340101", "Quản trị Kinh doanh", "A00, A01, D01, D07", 27.0, "UEH")
        )
        majors.forEach { major ->
            val cv = ContentValues().apply {
                put(MAJOR_CODE, major.code)
                put(MAJOR_NAME, major.name)
                put(MAJOR_BLOCK, major.admissionBlock)
                put(MAJOR_SCORE, major.benchmarkScore)
                put(MAJOR_UNI_CODE, major.universityCode)
            }
            db?.insert(TBL_MAJOR, null, cv)
        }
    }

    // ------------------- HÀM LẤY DỮ LIỆU -------------------
    fun getAllUniversities(): List<University> {
        val universities = mutableListOf<University>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TBL_UNI", null)
        if (cursor.moveToFirst()) {
            do {
                universities.add(
                    University(
                        code = cursor.getString(cursor.getColumnIndexOrThrow(UNI_CODE)),
                        name = cursor.getString(cursor.getColumnIndexOrThrow(UNI_NAME)),
                        address = cursor.getString(cursor.getColumnIndexOrThrow(UNI_ADDRESS)),
                        phone = cursor.getString(cursor.getColumnIndexOrThrow(UNI_PHONE)),
                        website = cursor.getString(cursor.getColumnIndexOrThrow(UNI_WEBSITE))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return universities
    }

    fun searchAndFilterMajors(
        query: String = "",
        universityCode: String? = null,
        admissionBlock: String? = null,
        suggestedScore: Double? = null,
        showOnlyFavorites: Boolean = false
    ): List<MajorInfo> {
        val majorList = mutableListOf<MajorInfo>()
        val db = readableDatabase

        val baseQuery = "SELECT M.$MAJOR_ID, M.$MAJOR_CODE, M.$MAJOR_NAME, " +
                "U.$UNI_NAME, M.$MAJOR_BLOCK, M.$MAJOR_SCORE, F.$FAV_MAJOR_ID " +
                "FROM $TBL_MAJOR M " +
                "JOIN $TBL_UNI U ON M.$MAJOR_UNI_CODE = U.$UNI_CODE " +
                "LEFT JOIN $TBL_FAVORITE F ON M.$MAJOR_ID = F.$FAV_MAJOR_ID"

        val conditions = mutableListOf<String>()
        val selectionArgs = mutableListOf<String>()

        if (query.isNotBlank()) {
            conditions.add("(M.$MAJOR_NAME LIKE ? OR M.$MAJOR_CODE LIKE ?)")
            selectionArgs.add("%$query%")
            selectionArgs.add("%$query%")
        }
        if (universityCode != null && universityCode != "ALL") {
            conditions.add("M.$MAJOR_UNI_CODE = ?")
            selectionArgs.add(universityCode)
        }
        if (admissionBlock != null && admissionBlock != "ALL") {
            conditions.add("M.$MAJOR_BLOCK LIKE ?")
            selectionArgs.add("%$admissionBlock%")
        }
        if (suggestedScore != null) {
            conditions.add("M.$MAJOR_SCORE <= ?")
            selectionArgs.add((suggestedScore + 1).toString())
        }
        if (showOnlyFavorites) {
            conditions.add("F.$FAV_MAJOR_ID IS NOT NULL")
        }

        val finalQuery = if (conditions.isNotEmpty()) {
            "$baseQuery WHERE ${conditions.joinToString(" AND ")} ORDER BY M.$MAJOR_SCORE DESC"
        } else {
            "$baseQuery ORDER BY M.$MAJOR_SCORE DESC"
        }

        val cursor = db.rawQuery(finalQuery, selectionArgs.toTypedArray())
        if (cursor.moveToFirst()) {
            do {
                majorList.add(
                    MajorInfo(
                        majorId = cursor.getInt(cursor.getColumnIndexOrThrow(MAJOR_ID)),
                        majorCode = cursor.getString(cursor.getColumnIndexOrThrow(MAJOR_CODE)),
                        majorName = cursor.getString(cursor.getColumnIndexOrThrow(MAJOR_NAME)),
                        universityName = cursor.getString(cursor.getColumnIndexOrThrow(UNI_NAME)),
                        admissionBlock = cursor.getString(cursor.getColumnIndexOrThrow(MAJOR_BLOCK)),
                        benchmarkScore = cursor.getDouble(cursor.getColumnIndexOrThrow(MAJOR_SCORE)),
                        isFavorite = !cursor.isNull(cursor.getColumnIndexOrThrow(FAV_MAJOR_ID))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return majorList
    }

    fun getAllBlocks(): List<String> {
        val blocks = mutableListOf<String>()
        blocks.add("Tất cả") // Cho phép chọn tất cả

        val db = readableDatabase
        val cursor = db.rawQuery("SELECT DISTINCT $MAJOR_BLOCK FROM $TBL_MAJOR", null)

        if (cursor.moveToFirst()) {
            do {
                val block = cursor.getString(cursor.getColumnIndexOrThrow(MAJOR_BLOCK))
                blocks.add(block)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return blocks
    }


    // ------------------- HÀM YÊU THÍCH -------------------
    fun addFavorite(majorId: Int) {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put(FAV_MAJOR_ID, majorId)
        }
        db.insertWithOnConflict(TBL_FAVORITE, null, cv, SQLiteDatabase.CONFLICT_IGNORE)
    }

    fun removeFavorite(majorId: Int) {
        val db = writableDatabase
        db.delete(TBL_FAVORITE, "$FAV_MAJOR_ID=?", arrayOf(majorId.toString()))
    }

    fun isFavorite(majorId: Int): Boolean {
        val db = readableDatabase
        val cursor = db.query(
            TBL_FAVORITE,
            arrayOf(FAV_ID),
            "$FAV_MAJOR_ID=?",
            arrayOf(majorId.toString()),
            null, null, null
        )
        val exists = cursor.moveToFirst()
        cursor.close()
        return exists
    }
}

