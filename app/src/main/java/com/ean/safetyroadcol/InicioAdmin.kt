package com.ean.safetyroadcol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class InicioAdmin : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_admin)
        auth = Firebase.auth

        val usuario:String = intent.getStringExtra("USUARIO").toString()
        val perfil:String = intent.getStringExtra("PERFIL").toString()

        val boton_registrarUsuario = findViewById<Button>(R.id.BtnRegistrarUsuario)
        boton_registrarUsuario.setOnClickListener{
            val intent = Intent(this,RegistrarUsuario::class.java)
            startActivity(intent)
        }

        val boton_cerrarsesion = findViewById<Button>(R.id.BtnCerrarSesion)
        boton_cerrarsesion.setOnClickListener{
            auth.signOut()
            if (auth.currentUser==null){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}