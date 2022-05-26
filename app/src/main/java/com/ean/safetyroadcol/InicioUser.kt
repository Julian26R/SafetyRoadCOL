package com.ean.safetyroadcol

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class InicioUser : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {

        val db = Firebase.firestore
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_user)

        auth = Firebase.auth

        val usuario:String = intent.getStringExtra("USUARIO").toString()
        val perfil:String = intent.getStringExtra("PERFIL").toString()
        val btn_estado=findViewById<Button>(R.id.btn_estado_user)
        val textoestado=findViewById<TextView>(R.id.et_vistaestado_user)
        val imagen=findViewById<ImageView>(R.id.im_user_user)
        val mensaje=findViewById<TextView>(R.id.tv_asignado_user)
            var texto=""
        val progress=findViewById<ProgressBar>(R.id.progressBar_user)
        val boton_cerrar_sesion=findViewById<Button>(R.id.btn_cerrar_user)
        val sdf = SimpleDateFormat("ddMMyyyy")
        val currentDate = sdf.format(Date())
    val inspeccion="${usuario}_$currentDate"

        boton_cerrar_sesion.setOnClickListener{
            progress.visibility=View.VISIBLE
            auth.signOut()//cierra sesion
            val intent= Intent(this,MainActivity::class.java)
            progress.visibility=View.INVISIBLE
            startActivity(intent)
        }
        val docRef1 = db.collection("Inspeccion")
        docRef1.get()
            .addOnSuccessListener { document ->
                for (doc in document){
                    if(doc.id==inspeccion){
                        textoestado.text = "Informacion Diligenciada!!"
                        btn_estado.visibility = View.INVISIBLE
                    }
                }

                if(textoestado.text==""){
                    textoestado.text="Pendiente por diligenciar"
                    btn_estado.visibility=View.VISIBLE
                }
            }
            .addOnFailureListener { exception ->
                textoestado.text="Pendiente por diligenciar"
                btn_estado.visibility=View.VISIBLE
            }
        val docRef = db.collection("Vehiculo")
            .get()
            .addOnSuccessListener { documentos ->
                for (document in documentos){
                    val placa=document.id
                    val asignadoA=document.get("asignadoA")
                    val fechaAsignacion=document.get("fechaAsignacion")
                    if(asignadoA==usuario){
                        texto="Asignado el vehiculo $placa $inspeccion"
                    }
                }
                if(texto!=""){
                    btn_estado.visibility = View.VISIBLE
                    imagen.setImageResource(R.drawable.vehiculo)
                }else{
                    btn_estado.visibility = View.INVISIBLE
                    texto="Sin vehiculo asociado $inspeccion"
                    imagen.setImageResource(R.drawable.noencontrado)
                }
                mensaje.text="$texto"

            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

    }

}