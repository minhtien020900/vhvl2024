package com.example.a02_buoi2
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login: AppCompatActivity(), View.OnClickListener {
    private lateinit var ipUser: EditText
    private lateinit var ipPwd: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        ipUser = findViewById(R.id.username)
        ipPwd = findViewById(R.id.password)
        val btnLogin: Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin ->{
                val username = ipUser.text.toString()
                val pwd = ipPwd.text.toString()

                System.out.print(username)
                System.out.print(pwd)

                if(username.equals("admin") && pwd.equals("admin")){
                    Toast.makeText(this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}