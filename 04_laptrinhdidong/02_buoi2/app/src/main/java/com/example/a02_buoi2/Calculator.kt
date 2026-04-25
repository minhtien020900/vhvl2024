package com.example.a02_buoi2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Calculator : AppCompatActivity(), View.OnClickListener {
    private lateinit var ipNum1: EditText
    private lateinit var ipNum2: EditText
    private lateinit var ipResult: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        ipNum1 = findViewById(R.id.number1)
        ipNum2 = findViewById(R.id.number2)
        ipResult = findViewById(R.id.result)
        val btnPlus: Button = findViewById(R.id.btnPlus)
        val btnMinus: Button = findViewById(R.id.btnMinus)
        val btnDiv: Button = findViewById(R.id.btnDiv)
        val btnMulti: Button = findViewById(R.id.btnMultiplication)

        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        btnDiv.setOnClickListener(this)
        btnMulti.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val num1 = ipNum1.text.toString().toInt()
        val num2 = ipNum2.text.toString().toInt()
        var rs = 0
        when (v?.id) {
            R.id.btnPlus -> {
                rs = num1 + num2
                rs.toString()
                ipResult.setText(""+rs)
            }

            R.id.btnMinus -> {
                rs = num1 - num2
                rs.toString()
                ipResult.setText(""+rs)
            }

            R.id.btnDiv -> {
                rs = num1 * num2
                rs.toString()
                ipResult.setText(""+rs)
            }

            R.id.btnMultiplication -> {
                rs = num1 / num2
                rs.toString()
                ipResult.setText(""+rs)
            }

        }
    }
}