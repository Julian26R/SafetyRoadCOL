package com.ean.safetyroadcol

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AsignarVehiculo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asignar_vehiculo)

        val db = Firebase.firestore
        val boton_regresar = findViewById<Button>(R.id.BtnVolverAsignacion)
        boton_regresar.setOnClickListener{
            val intent = Intent(this,InicioAdmin::class.java)
            startActivity(intent)
        }
        val Txt_Placa = findViewById<EditText>(R.id.EdTxtPlacaAsignacion)
        val Txt_Correo = findViewById<EditText>(R.id.EdTxtCorreoResponsable)
        val boton_registra = findViewById<Button>(R.id.BtnAsignar)
        boton_registra.setOnClickListener {
            try {
                val Placa = Txt_Placa.text.toString()
                val Correo = Txt_Correo.text.toString().lowercase()
                if(Placa.isEmpty() || Correo.isEmpty()){
                    throw Exception ("Placa y Correo no pueden estar vacios")
                }else{

                    val docRef = db.collection("Vehiculo").document(Placa)
                    docRef.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                val placa= document.get("placa")
                                val marca= document.get("marca")
                                val modelo = document.get("modelo")
                                val color = document.get("color")
                                val linea = document.get("linea")
                                val tipo = document.get("tipo")
                                val cilindraje = document.get("cilindraje")
                                val asignado = document.get("asignadoA")
                                if(asignado == Correo || asignado != Correo && asignado != ""){
                                    Toast.makeText(baseContext, "vehiculo asignado a : $asignado", Toast.LENGTH_SHORT).show()
                                }else{
                                    val asignadoa = hashMapOf(
                                        "asignadoA" to "$Correo",
                                        "placa" to "$placa",
                                        "marca" to "$marca",
                                        "modelo" to "$modelo",
                                        "color" to "$color",
                                        "linea" to "$linea",
                                        "tipo" to "$tipo",
                                        "cilindraje" to "$cilindraje"
                                    )
                                    docRef.set(asignadoa)
                                    val intent = Intent(this,InicioAdmin::class.java)
                                    startActivity(intent)
                                    Toast.makeText(baseContext, "Se asigno el vehiculo con la placa $Placa a : $Correo",
                                        Toast.LENGTH_SHORT).show()
                                }

                            }else {
                                Log.d(ContentValues.TAG, "El vehiculo no existe!")
                            }

                        }
                        .addOnFailureListener { e ->
                            Log.d(ContentValues.TAG, "Error adding document", e)
                        }
                }

            }catch(e:Exception){
                Toast.makeText(this, e.message,
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}