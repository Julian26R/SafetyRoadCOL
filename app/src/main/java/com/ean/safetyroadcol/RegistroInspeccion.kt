package com.ean.safetyroadcol

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class RegistroInspeccion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_inspeccion)
        val db = Firebase.firestore

        //lista luces
        val spluces = findViewById<Spinner>(R.id.sp_luces_ins)
        val eluces = listOf("Buen estado","Mal estado","Como se recibio","N/A")
        val adluces = ArrayAdapter(this,android.R.layout.simple_spinner_item,eluces)
        spluces.adapter = adluces
        val opluces = spluces.selectedItemPosition

        //lista espejo
        val spespejo = findViewById<Spinner>(R.id.sp_espejos_ins)
        val eespejo = listOf("Buen estado","Mal estado","Como se recibio","N/A")
        val adespejo = ArrayAdapter(this,android.R.layout.simple_spinner_item,eespejo )
        spespejo.adapter = adespejo
        val opespejo = spespejo.selectedItemPosition

        //lista abolla
        val spabolla = findViewById<Spinner>(R.id.sp_abolla_ins)
        val eabolla = listOf("Buen estado","Mal estado","Como se recibio","N/A")
        val adabolla = ArrayAdapter(this,android.R.layout.simple_spinner_item,eabolla)
        spabolla.adapter = adabolla
        val opabolla = spabolla.selectedItemPosition

        //lista llantas
        val spllantas = findViewById<Spinner>(R.id.sp_llantas_ins)
        val ellantas = listOf("Buen estado","Mal estado","Como se recibio","N/A")
        val adllantas = ArrayAdapter(this,android.R.layout.simple_spinner_item,ellantas)
        spllantas.adapter = adllantas
        val opllantas = spllantas.selectedItemPosition


        val placa: String = intent.getStringExtra("PLACA").toString().uppercase()
        val usuario: String = intent.getStringExtra("USUARIO").toString()
        val perfil:String = intent.getStringExtra("PERFIL").toString()
        val tv_Vehiculo = findViewById<TextView>(R.id.tv_Vehiculo_ins)
        val txt_obs=findViewById<EditText>(R.id.et_obs_ins)
        try {
            tv_Vehiculo.text = "Vehiculo asignado: $placa"
        } catch (e: java.lang.Exception) {


        }
        val btn_volver= findViewById<Button>(R.id.btn_volver_ins)
        btn_volver.setOnClickListener {
            val intent = Intent(this,InicioUser::class.java)
            intent.putExtra("USUARIO","$usuario")
            intent.putExtra("PERFIL","$perfil")
            startActivity(intent)
        }
        val btn_registra=findViewById<Button>(R.id.btn_registrar_ins)
        btn_registra.setOnClickListener {
            try {
                if(1==0){
                    throw Exception ("Placa y Correo no pueden estar vacios")
                }else{
                    val sdf = SimpleDateFormat("ddMMyyyy")
                    val currentDate = sdf.format(Date())
                    val inspeccion="${usuario}_$currentDate"
                    val tespejo:String = when{
                        opespejo == 0 -> "Buen estado"
                        opespejo == 1 -> "Mal estado"
                        opespejo == 2 -> "Como se recibio"
                        else -> "N/A"
                    }
                    val tluces:String = when{
                        opluces == 0 -> "Buen estado"
                        opluces == 1 -> "Mal estado"
                        opluces == 2 -> "Como se recibio"
                        else -> "N/A"
                    }
                    val tabolla:String = when{
                        opabolla == 0 -> "Buen estado"
                        opabolla == 1 -> "Mal estado"
                        opabolla == 1 -> "Como se recibio"
                        else -> "N/A"
                    }
                    val tllantas:String = when{
                        opllantas == 0 -> "Buen estado"
                        opllantas == 1 -> "Mal estado"
                        opllantas == 2 -> "Como se recibio"
                        else -> "N/A"
                    }
                    val observacion=txt_obs.text.toString()
                    val vehiculo = hashMapOf(
                        "luces" to "$tluces",
                        "espejo" to "$tespejo",
                        "abolla" to "$tabolla",
                        "llantas" to "$tllantas",
                        "observacion" to "$observacion"
                    )

                    val docRef = db.collection("Inspeccion").document(inspeccion)
                    docRef.set(vehiculo)
                        .addOnSuccessListener { documentReference ->
                            Log.d(ContentValues.TAG, "Se realiza el registro de la inspección $inspeccion : $placa")
                            val intent = Intent(this,InicioUser::class.java)
                            intent.putExtra("USUARIO","$usuario")
                            intent.putExtra("PERFIL","$perfil")
                            startActivity(intent)
                            Toast.makeText(baseContext, "Se realiza el registro de la inspección $inspeccion : $placa",
                                Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Log.w(ContentValues.TAG, "Error adding document", e)
                        }
                }

            }catch(e:Exception){
               Toast.makeText(this, e.message,
                    Toast.LENGTH_SHORT).show()
         }
    }
    }
}