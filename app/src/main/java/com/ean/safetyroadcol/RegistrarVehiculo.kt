package com.ean.safetyroadcol

import android.content.ContentValues.TAG
import android.content.Intent
import android.media.tv.TvView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrarVehiculo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_vehiculo)
        val db = Firebase.firestore

        val boton_regresar = findViewById<Button>(R.id.BtnVolverVehiculo)
        boton_regresar.setOnClickListener{
            val intent = Intent(this,InicioAdmin::class.java)
            startActivity(intent)
        }
        val TxtPlaca = findViewById<EditText>(R.id.EdTxtPlaca)
        val TxtMarca = findViewById<EditText>(R.id.EdTxtMarca)
        val TxtModelo = findViewById<EditText>(R.id.EdTxtModelo)
        val TxtColor = findViewById<EditText>(R.id.EdTxtColor)
        val TxtLinea = findViewById<EditText>(R.id.EdTxtLinea)

        val spinner = findViewById<Spinner>(R.id.SpClase)
        val elementos = listOf("Auto Movil","Motocicleta")
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,elementos)
        spinner.adapter = adaptador
        val opcion = spinner.selectedItemPosition

        val TxtCilindrje = findViewById<EditText>(R.id.EdTxtCilindraje)
        val boton_registrarvehiculo = findViewById<Button>(R.id.BtnRegistrarVehiculoNuevo)
        boton_registrarvehiculo.setOnClickListener {
            try{
                val Placa = TxtPlaca.text.toString()
                val Marca = TxtMarca.text.toString()
                val Modelo = TxtModelo.text.toString()
                val Color = TxtColor.text.toString()
                val Linea = TxtLinea.text.toString()
                val tipo:String = when{
                    opcion == 0 -> "Auto Movil"
                    opcion == 1 -> "Motocicleta"
                    else -> "error"
                }
                val Cilindraje = TxtCilindrje.text.toString()
                if(Placa.isEmpty() || Marca.isEmpty() || Modelo.isEmpty() || Color.isEmpty() || Linea.isEmpty() || tipo.isEmpty()){
                    throw Exception ("Placa, Marca, Modelo, Color,Linea y Tipo no pueden estar vacios")
                }else{
                    val vehiculo = hashMapOf(
                        "placa" to "$Placa",
                        "marca" to "$Marca",
                        "modelo" to "$Modelo",
                        "color" to "$Color",
                        "linea" to "$Linea",
                        "tipo" to "$tipo",
                        "cilindraje" to "$Cilindraje"
                    )

                    val docRef = db.collection("Vehiculo").document(Placa)
                    docRef.set(vehiculo)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "Se registro el vehiculo con la placa : $Placa")
                            val intent = Intent(this,InicioAdmin::class.java)
                            startActivity(intent)
                            Toast.makeText(baseContext, "Se registro el vehiculo con la placa : $Placa",
                                Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                }

            }catch(e:Exception){
                Toast.makeText(this, e.message,
                    Toast.LENGTH_SHORT).show()
            }

        }
    }
}