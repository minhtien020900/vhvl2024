package com.example.a02_buoi2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var img: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btn1: Button = findViewById(R.id.button)
        val btn2: Button = findViewById(R.id.button2)
        val btnShowLogin:Button = findViewById(R.id.showLogin)
        val btnShowCalculator:Button = findViewById(R.id.showCalculator)


        img=findViewById(R.id.imageView)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btnShowLogin.setOnClickListener(this)
        btnShowCalculator.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.button ->{

                img.setImageResource(R.drawable.think)
                img.visibility=View.VISIBLE
            }
            R.id.button2 ->{
                img.setImageResource(R.drawable.hehe)
                img.visibility=View.VISIBLE
            }
            R.id.showLogin ->{
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }R.id.showCalculator ->{
                val intent = Intent(this, Calculator::class.java)
                startActivity(intent)
            }
        }
    }
}