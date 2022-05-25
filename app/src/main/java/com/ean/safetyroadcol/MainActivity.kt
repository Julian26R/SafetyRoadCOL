package com.ean.safetyroadcol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val boton_login=findViewById<Button>(R.id.BtnLogin_am)
        boton_login.setOnClickListener {
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}