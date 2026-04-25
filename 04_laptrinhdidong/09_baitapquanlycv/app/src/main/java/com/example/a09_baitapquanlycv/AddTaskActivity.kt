package com.example.a09_baitapquanlycv

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addTaskView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val dbHelper = DBHelper(this)
        val btnSave = findViewById<Button>(R.id.save)
        val taskName = findViewById<EditText>(R.id.taskName)
        val taskDesc = findViewById<EditText>(R.id.taskDesc)
        val statusCheckBox = findViewById<CheckBox>(R.id.statusAdd)
        btnSave.setOnClickListener {
            val name = taskName.text.toString()
            val desc = taskDesc.text.toString()

            val checkboxValue: Int = if (statusCheckBox.isChecked) {
                1
            } else {
                0
            }
            dbHelper.addTask(name,desc,checkboxValue)

        }
    }
}