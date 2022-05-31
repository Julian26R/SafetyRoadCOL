package com.ean.safetyroadcol

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.lang.Exception
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

class InicioUser : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    private lateinit var storage: FirebaseStorage;
    override fun onCreate(savedInstanceState: Bundle?) {

        val db = Firebase.firestore
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_user)

        auth = Firebase.auth
        storage=Firebase.storage
        var storageRef = storage.reference

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
        val btn_informacion=findViewById<Button>(R.id.btn_informacion_user)

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
                            intent.putExtra("PERFIL","$perfil")
                            startActivity(intent)
                        }
                        btn_informacion.setOnClickListener {
                            val intent= Intent(this,InfoVehiculo::class.java)
                            intent.putExtra("PLACA","$placa")
                            intent.putExtra("USUARIO","$usuario")
                            intent.putExtra("PERFIL","$perfil")
                            startActivity(intent)
                        }
                        texto="Asignado el vehiculo $placa "
                        //Toma imagen del vehiculo
                        try {
                            val pathReference = storageRef.child("vehiculo/$placa.png")

                            //val uri = pathReference.path
                            //val pats = "${uri.replace("vehiculo/","vehiculo%2F")}?"
                            pathReference.downloadUrl.addOnSuccessListener { urii ->
                                val okis=urii.query
                                Glide.with(this)
                                    //.load("https://firebasestorage.googleapis.com/v0/b/safetyroadcol.appspot.com/o/vehiculo%2FAAA-111.png?alt=media&token=c0069fbe-555c-4c5c-b426-b5c55f9e181b")
                                    .load(urii)
                                    .fitCenter()
                                    .centerCrop()
                                    .into(imagen)
                            }
                                .addOnFailureListener {
                                    imagen.setImageResource(R.drawable.vehiculo)
                                }


                        }catch(e:Exception){
                            Toast.makeText(this, e.message,
                                Toast.LENGTH_SHORT).show()
                            imagen.setImageResource(R.drawable.vehiculo)
                        }

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
                    //imagen.setImageResource(R.drawable.vehiculo)
                }else{
                    btn_estado.visibility = View.INVISIBLE
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