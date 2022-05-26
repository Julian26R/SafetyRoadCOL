package com.ean.safetyroadcol

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrarUsuario : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)
        val db = Firebase.firestore
        auth = Firebase.auth

        val boton_regresar = findViewById<Button>(R.id.BtnVolverNuevoUsuario)
        boton_regresar.setOnClickListener{
            val intent = Intent(this,InicioAdmin::class.java)
            startActivity(intent)
        }

        val txt_correo = findViewById<EditText>(R.id.EdTxtCorreoNuevoUsuario)
        val txt_contraseña = findViewById<EditText>(R.id.EdTxtContraseñaNuevoUsuario)
        val txt_repetircontra = findViewById<EditText>(R.id.EdTxtRepetirContra)
        val boton_registrar = findViewById<Button>(R.id.BtnRegistrarNuevoUsuario)

        fun contraseñas_iguales (contraseña_uno:String,contraseña_dos:String):Boolean{
            return contraseña_uno==contraseña_dos
        }
        boton_registrar.setOnClickListener{
            try {
                val correo = txt_correo.text.toString().lowercase()
                val contra = txt_contraseña.text.toString()
                val repetircontraseña = txt_repetircontra.text.toString()
                if(correo.isEmpty() || contra.isEmpty() || contra.isEmpty()){
                    throw Exception("los campos no pueden estar vacios !!")
                }else{
                    if(contraseñas_iguales(contra,repetircontraseña)){
                        auth.createUserWithEmailAndPassword(correo,contra)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {

                                    val user = hashMapOf(
                                        "ID_Empresa" to 1,
                                        "correo" to "$correo",
                                        "perfil" to "user"
                                    )
                                    val docRef = db.collection("Usuario").document(correo)
                                        docRef.set(user)
                                        .addOnSuccessListener { document ->
                                            Log.d(TAG, "DocumentSnapshot added with ID: ${correo}")
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w(TAG, "Error adding document", e)
                                        }
                                    val intent = Intent(this,InicioAdmin::class.java)
                                    startActivity(intent)
                                    Toast.makeText(baseContext, "Usuario Creado",
                                        Toast.LENGTH_SHORT).show()
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithCustomToken:success")

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                                    Toast.makeText(baseContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show()
                                }
                            }
                    }else{
                        throw Exception("Las contraseñas deben ser iguales !!")
                    }
                }
            }catch (e:Exception){
                Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }
}