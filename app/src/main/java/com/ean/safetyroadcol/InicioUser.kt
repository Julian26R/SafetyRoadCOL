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
import com.google.firebase.storage.ktx.storage
import java.text.SimpleDateFormat
import java.util.*

class InicioUser : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {

        val db = Firebase.firestore
        var storage = Firebase.storage
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_user)

        auth = Firebase.auth

        val usuario:String = intent.getStringExtra("USUARIO").toString()
        val perfil:String = intent.getStringExtra("PERFIL").toString()
        val btn_estado=findViewById<Button>(R.id.btn_estado_user)
        val textoestado=findViewById<TextView>(R.id.et_vistaestado_user)
        val imagen=findViewById<ImageView>(R.id.im_user_user)
        val mensaje=findViewById<TextView>(R.id.tv_asignado_user)
        val im_soat = findViewById<ImageView>(R.id.imgsoat)
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

        val docRef = db.collection("Vehiculo")
            .get()
            .addOnSuccessListener { documentos ->
                for (document in documentos){
                    val placa=document.id
                    val asignadoA=document.get("asignadoA")
                    val fechaAsignacion=document.get("fechaAsignacion")
                    if(asignadoA==usuario){
                        btn_estado.setOnClickListener {
                            val intent= Intent(this,RegistroInspeccion::class.java)
                            intent.putExtra("PLACA","$placa")
                            intent.putExtra("USUARIO","$usuario")
                            startActivity(intent)
                        }
                        im_soat.setOnClickListener{
                            val soat_pdf = "$placa.pdf"
                            val intent = Intent(this,LeerLibro::class.java)
                            Toast.makeText(this,"$soat_pdf",Toast.LENGTH_SHORT).show()
                            intent.putExtra("TITULO LIBRO", "$soat_pdf")//enviando el nombre del libro a leer libro
                            //enviar datos extra
                            startActivity(intent)
                        }
                        texto="Asignado el vehiculo $placa "
                        val docRef1 = db.collection("Inspeccion")
                        docRef1.get()
                            .addOnSuccessListener { document ->
                                for (doc in document){
                                    if(doc.id==inspeccion){
                                        textoestado.text = "Información Diligenciada!!"
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
                    }

                }
                if(texto!=""){
                    btn_estado.visibility = View.VISIBLE
                    im_soat.visibility=View.VISIBLE
                    imagen.setImageResource(R.drawable.vehiculo)
                }else{
                    btn_estado.visibility = View.INVISIBLE
                    im_soat.visibility = View.INVISIBLE
                    textoestado.text=""
                    texto="Sin vehiculo asociado "
                    imagen.setImageResource(R.drawable.noencontrado)
                }
                mensaje.text="$texto"

            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

    }

}