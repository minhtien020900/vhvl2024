package com.example.a05_buoi5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editText = findViewById<EditText>(R.id.editText)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnBaitap2 = findViewById<Button>(R.id.bt2)

        val filename = "myfile.txt"

        try {
            btnSave.setOnClickListener {
                val fileContents = editText.text.toString()
                this.openFileOutput(filename, Context.MODE_PRIVATE).use {
                    it.write(fileContents.toByteArray())
                }
                Toast.makeText(applicationContext, "File saved successfully!", Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "Error saving file: ${e.message}", Toast.LENGTH_LONG)
                .show()
        }

        btnBaitap2.setOnClickListener {
            startActivity(Intent(this, Baitap2::class.java))
        }
    }
}