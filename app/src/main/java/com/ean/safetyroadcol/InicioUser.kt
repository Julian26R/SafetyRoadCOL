package com.ean.safetyroadcol

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InicioUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Firebase.firestore
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_user)
        val usuario:String = intent.getStringExtra("USUARIO").toString()
        val perfil:String = intent.getStringExtra("PERFIL").toString()
        val btn_estado=findViewById<Button>(R.id.btn_estado_user)
        val imagen=findViewById<ImageView>(R.id.im_user_user)
        val mensaje=findViewById<TextView>(R.id.tv_asignado_user)
            var texto=""
        val docRef = db.collection("Vehiculo")
            .get()
            .addOnSuccessListener { documentos ->
                for (document in documentos){
                    val placa=document.id
                    val asignadoA=document.get("asignadoA")
                    val fechaAsignacion=document.get("fechaAsignacion")
                    if(asignadoA==usuario){
                        texto="Asignado el vehiculo $placa"
                    }
                }
                if(texto!=""){
                    btn_estado.visibility = View.VISIBLE
                    imagen.setImageResource(R.drawable.logo)
                }else{
                    btn_estado.visibility = View.INVISIBLE
                    texto="Sin vehiculo asociado"
                    imagen.setImageResource(R.drawable.ic_launcher_background)
                }
                mensaje.text="$texto"

            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }
}