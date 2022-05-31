package com.ean.safetyroadcol

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.lang.Exception

class InfoVehiculo : AppCompatActivity() {
    private lateinit var storage: FirebaseStorage;
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_vehiculo)
        val db = Firebase.firestore
        var storageRef = storage.reference
        val usuario:String = intent.getStringExtra("USUARIO").toString()
        val perfil:String = intent.getStringExtra("PERFIL").toString()
        val placa:String = intent.getStringExtra("PLACA").toString()
        val btn_volver= findViewById<Button>(R.id.btn_volver_info)
        btn_volver.setOnClickListener {
            val intent= Intent(this,InicioUser::class.java)
            intent.putExtra("USUARIO","$usuario")
            intent.putExtra("PERFIL","$perfil")
            startActivity(intent)
        }
        val tv_placa=findViewById<TextView>(R.id.tv_placa_info)
        val tv_asignado=findViewById<TextView>(R.id.tv_asignado_info)
        val tv_cilindraje=findViewById<TextView>(R.id.tv_cilindraje_info)
        val tv_color=findViewById<TextView>(R.id.tv_color_info)
        val tv_linea=findViewById<TextView>(R.id.tv_linea_info)
        val tv_marca=findViewById<TextView>(R.id.tv_marca_info)
        val tv_modelo=findViewById<TextView>(R.id.tv_modelo_info)
        val tv_tipo=findViewById<TextView>(R.id.tv_tipo_info)
        try {
            val docRef = db.collection("Vehiculo").document(placa)
                .get()
                .addOnSuccessListener { document ->
                    //for (document in documentos) {
                        val placa1 = document.id
                        val asignadoA = document.get("asignadoA")
                        if (asignadoA == usuario && placa1 == placa) {
                            tv_placa.text = "$placa"
                            tv_asignado.text = "$asignadoA"
                            tv_cilindraje.text = "${document.get("cilindraje")}"
                            tv_color.text = "${document.get("color")}"
                            tv_linea.text = "${document.get("linea")}"
                            tv_marca.text = "${document.get("marca")}"
                            tv_modelo.text = "${document.get("modelo")}"
                            tv_tipo.text = "${document.get("tipo")}"
                            Toast.makeText(this, "Cargado!",
                                Toast.LENGTH_SHORT).show()
                        }
                    //}

                }
                .addOnFailureListener { exception ->
                    Log.d(ContentValues.TAG, "get failed with ", exception)
                    Toast.makeText(this, "get failed with ${exception.message}",
                        Toast.LENGTH_SHORT).show()
                }
            // Create a reference with an initial file path and name
            val pathReference = storageRef.child("images/AAA-111.png")
            val listRef = storage.reference.child("files/uid")

// You'll need to import com.google.firebase.storage.ktx.component1 and
// com.google.firebase.storage.ktx.component2
            /*listRef.listAll()
                .addOnSuccessListener { (items, prefixes) ->
                    prefixes.forEach { prefix ->
                        // All the prefixes under listRef.
                        // You may call listAll() recursively on them.
                    }

                    items.forEach { item ->
                        // All the items under listRef.
                    }
                }
                .addOnFailureListener {
                    // Uh-oh, an error occurred!
                }*/

        }catch(e:Exception){
            Toast.makeText(this, e.message,
                Toast.LENGTH_SHORT).show()
        }

    }
}