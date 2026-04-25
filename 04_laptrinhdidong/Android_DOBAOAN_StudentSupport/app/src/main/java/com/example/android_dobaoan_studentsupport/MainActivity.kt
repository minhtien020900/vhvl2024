package com.example.android_dobaoan_studentsupport

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var majorAdapter: MajorAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var spinnerUniversity: Spinner
    private lateinit var spinnerBlock: Spinner
    private lateinit var etScore: EditText
    private lateinit var btnSuggest: Button
    private lateinit var btnShowFavorites: Button

    private var currentQuery: String = ""
    private var currentUniversity: String? = null
    private var currentBlock: String? = null
    private var currentSuggestedScore: Double? = null
    private var showOnlyFavorites: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Khởi tạo DB và view
        dbHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        spinnerUniversity = findViewById(R.id.spinnerUniversity)
        spinnerBlock = findViewById(R.id.spinnerBlock)
        etScore = findViewById(R.id.etScore)
        btnSuggest = findViewById(R.id.btnSuggest)
        btnShowFavorites = findViewById(R.id.btnShowFavorites)

        setupRecyclerView()
        setupFilters()
        setupSearch()
        setupSuggestionButton()
        setupFavoritesButton()

        // Tải dữ liệu ban đầu
        loadData()
    }

    private fun setupRecyclerView() {
        majorAdapter = MajorAdapter(emptyList()) { major ->
            // Xử lý khi click vào nút yêu thích
            if (major.isFavorite) {
                dbHelper.removeFavorite(major.majorId)
            } else {
                dbHelper.addFavorite(major.majorId)
            }
            loadData()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = majorAdapter
    }

    private fun setupFilters() {
        // Spinner danh sách trường
        val universities = dbHelper.getAllUniversities()
        val uniNames = mutableListOf("Tất cả")
        uniNames.addAll(universities.map { it.name})

        val uniAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, uniNames)
        uniAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUniversity.adapter = uniAdapter

        spinnerUniversity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long
            ) {
                currentUniversity =
                    if (position > 0) universities[position - 1].code else null // trừ 1 vì có "Tất cả"
                loadData()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                currentUniversity = null
                loadData()
            }
        }

        // Spinner khối thi
        val blocks = dbHelper.getAllBlocks()
        val blockAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, blocks)
        blockAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBlock.adapter = blockAdapter

        spinnerBlock.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long
            ) {
                currentBlock = if (position > 0) blocks[position] else null
                loadData()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                currentBlock = null
                loadData()
            }
        }
    }

    private fun setupSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                currentQuery = query ?: ""
                loadData()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                currentQuery = newText ?: ""
                loadData()
                return true
            }
        })
    }

    private fun setupSuggestionButton() {
        btnSuggest.setOnClickListener {
            val score = etScore.text.toString().toDoubleOrNull()
            if (score != null) {
                currentSuggestedScore = score
                loadData()
            } else {
                Toast.makeText(this, "Vui lòng nhập điểm hợp lệ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupFavoritesButton() {
        btnShowFavorites.setOnClickListener {
            showOnlyFavorites = !showOnlyFavorites
            btnShowFavorites.text =
                if (showOnlyFavorites) "Xem tất cả" else "Xem danh sách yêu thích"
            loadData()
        }
    }

    private fun loadData() {
        val majors = dbHelper.searchAndFilterMajors(
            query = currentQuery,
            universityCode = currentUniversity,
            admissionBlock = currentBlock,
            suggestedScore = currentSuggestedScore,
            showOnlyFavorites = showOnlyFavorites
        )
        majorAdapter.updateData(majors)
    }
}
