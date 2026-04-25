package com.example.baitap_180925

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var db: ExpenseDBHelper
    private lateinit var adapter: ExpenseAdapter

    private lateinit var edtTitle: EditText
    private lateinit var edtAmount: EditText
    private lateinit var btnPickDateTime: Button
    private lateinit var tvPickedDateTime: TextView
    private lateinit var btnAdd: Button

    private lateinit var btnStartDate: Button
    private lateinit var btnEndDate: Button
    private lateinit var btnFilter: Button
    private lateinit var btnClearFilter: Button

    private lateinit var tvTotal: TextView
    private lateinit var rvExpenses: RecyclerView

    // lưu datetime được chọn để thêm, theo millis
    private var pickedCalendar: Calendar? = null

    // lưu ngày bắt đầu/kết thúc cho lọc (chỉ date, time thiết lập khi lọc)
    private var filterStartDate: String? = null // "yyyy-MM-dd"
    private var filterEndDate: String? = null   // "yyyy-MM-dd"

    private val dbFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private val dateOnlyFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val displayDateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = ExpenseDBHelper(this)

        edtTitle = findViewById(R.id.edtTitle)
        edtAmount = findViewById(R.id.edtAmount)
        btnPickDateTime = findViewById(R.id.btnPickDateTime)
        tvPickedDateTime = findViewById(R.id.tvPickedDateTime)
        btnAdd = findViewById(R.id.btnAdd)

        btnStartDate = findViewById(R.id.btnStartDate)
        btnEndDate = findViewById(R.id.btnEndDate)
        btnFilter = findViewById(R.id.btnFilter)
        btnClearFilter = findViewById(R.id.btnClearFilter)

        tvTotal = findViewById(R.id.tvTotal)
        rvExpenses = findViewById(R.id.rvExpenses)

        rvExpenses.layoutManager = LinearLayoutManager(this)
        adapter = ExpenseAdapter(emptyList())
        rvExpenses.adapter = adapter

        // Hiển thị tất cả ban đầu
        loadAllExpenses()

        btnPickDateTime.setOnClickListener { pickDateTimeForAdd() }
        btnAdd.setOnClickListener { onAddClicked() }

        btnStartDate.setOnClickListener { pickDate(isStart = true) }
        btnEndDate.setOnClickListener { pickDate(isStart = false) }
        btnFilter.setOnClickListener { applyFilter() }
        btnClearFilter.setOnClickListener { clearFilter() }
    }

    private fun pickDateTimeForAdd() {
        val now = Calendar.getInstance()
        val dp = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val cal = Calendar.getInstance()
                cal.set(year, month, dayOfMonth)
                // sau khi chọn ngày, mở TimePicker
                TimePickerDialog(
                    this,
                    { _, hourOfDay, minute ->
                        cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        cal.set(Calendar.MINUTE, minute)
                        cal.set(Calendar.SECOND, 0)
                        pickedCalendar = cal
                        tvPickedDateTime.text = displayDateTimeFormat.format(cal.time)
                    },
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true
                ).show()
            },
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        )
        dp.show()
    }

    private fun onAddClicked() {
        val title = edtTitle.text.toString().trim()
        val amount = edtAmount.text.toString().toDoubleOrNull() ?: 0.0
        if (title.isEmpty()) {
            Toast.makeText(this, "Nhập tên chi tiêu", Toast.LENGTH_SHORT).show()
            return
        }
        val cal = pickedCalendar ?: Calendar.getInstance()
        val datetime = dbFormat.format(cal.time) // "yyyy-MM-dd HH:mm:ss"
        val e = Expense(title = title, amount = amount, datetime = datetime)
        db.addExpense(e)
        Toast.makeText(this, "Đã thêm", Toast.LENGTH_SHORT).show()

        // reset input
        edtTitle.text.clear()
        edtAmount.text.clear()
        pickedCalendar = null
        tvPickedDateTime.text = "(Chưa chọn)"

        // reload dữ liệu theo filter hiện tại (nếu có) hoặc show all
        if (filterStartDate != null && filterEndDate != null) applyFilter()
        else loadAllExpenses()
    }

    private fun pickDate(isStart: Boolean) {
        val now = Calendar.getInstance()
        val dp = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val cal = Calendar.getInstance()
                cal.set(year, month, dayOfMonth, 0, 0, 0)
                val dateStr = dateOnlyFormat.format(cal.time) // yyyy-MM-dd
                if (isStart) {
                    filterStartDate = dateStr
                    btnStartDate.text = "Bắt đầu: $dateStr"
                } else {
                    filterEndDate = dateStr
                    btnEndDate.text = "Kết thúc: $dateStr"
                }
            },
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        )
        dp.show()
    }

    private fun applyFilter() {
        val s = filterStartDate
        val e = filterEndDate
        if (s.isNullOrBlank() || e.isNullOrBlank()) {
            Toast.makeText(this, "Chọn ngày bắt đầu và kết thúc để lọc", Toast.LENGTH_SHORT).show()
            return
        }
        // start 00:00:00, end 23:59:59
        val startDatetime = "$s 00:00:00"
        val endDatetime = "$e 23:59:59"
        val list = db.getExpensesByRange(startDatetime, endDatetime)
        adapter.updateData(list)
        val total = db.getTotalByRange(startDatetime, endDatetime)
        tvTotal.text = "Tổng: ${String.format(Locale.getDefault(), "%,.0f", total)} đ"
    }

    private fun clearFilter() {
        filterStartDate = null
        filterEndDate = null
        btnStartDate.text = "Ngày bắt đầu"
        btnEndDate.text = "Ngày kết thúc"
        loadAllExpenses()
    }

    private fun loadAllExpenses() {
        val list = db.getAllExpenses()
        adapter.updateData(list)
        // tính tổng tất cả
        var total = 0.0
        for (i in list) total += i.amount
        tvTotal.text = "Tổng: ${String.format(Locale.getDefault(), "%,.0f", total)} đ"
    }
}