package com.ean.safetyroadcol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InicioAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_admin)
        val usuario:String = intent.getStringExtra("USUARIO").toString()
        val perfil:String = intent.getStringExtra("PERFIL").toString()

        val boton_registrarUsuario = findViewById<Button>(R.id.BtnRegistrarUsuario)
        boton_registrarUsuario.setOnClickListener{
            val intent = Intent(this,RegistrarUsuario::class.java)
            startActivity(intent)
        }
    }
}