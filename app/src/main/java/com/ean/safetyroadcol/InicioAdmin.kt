package com.ean.safetyroadcol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InicioAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_admin)
        val usuario:String = intent.getStringExtra("USUARIO").toString()
        val perfil:String = intent.getStringExtra("PERFIL").toString()
    }
}