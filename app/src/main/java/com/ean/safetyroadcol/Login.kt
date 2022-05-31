package com.ean.safetyroadcol

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        val db = Firebase.firestore
        if(auth.currentUser!=null) {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


        val txt_correo=findViewById<EditText>(R.id.ed_usuario_log)
        val txt_contrasena=findViewById<EditText>(R.id.et_contrasena_log)
        val boton_inicio_sesion=findViewById<Button>(R.id.btn_login_log)
        val progress=findViewById<ProgressBar>(R.id.progressBar_log)
        boton_inicio_sesion.setOnClickListener {
            progress.visibility= View.VISIBLE
            try {
                val correo=txt_correo.text.toString().lowercase()
                val contraseña=txt_contrasena.text.toString()
                if(correo.isEmpty()||contraseña.isEmpty()){
                    progress.visibility= View.INVISIBLE
                    throw Exception("Los campos no pueden estar vacios")
                }else{
                    auth.signInWithEmailAndPassword(correo,contraseña)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {

                                // Sign in success, update UI with the signed-in user's information
                                Log.d(ContentValues.TAG, "signInWithCustomToken:success")
                                val docRef = db.collection("Usuario").document(correo)
                                docRef.get()
                                    .addOnSuccessListener { document ->
                                        if (document != null) {
                                            val data=document.data
                                            val usuario= document.get("correo")
                                            val perfil= document.get("perfil")
                                            Toast.makeText(baseContext, "Bienvenido", Toast.LENGTH_SHORT).show()
                                            if(perfil=="admin"){
                                                progress.visibility= View.INVISIBLE
                                                val intent= Intent(this,InicioAdmin::class.java)
                                                intent.putExtra("USUARIO","$usuario")
                                                intent.putExtra("PERFIL","$perfil")
                                                startActivity(intent)
                                            }else{
                                                progress.visibility= View.INVISIBLE
                                                val intent= Intent(this,InicioUser::class.java)
                                                intent.putExtra("USUARIO","$usuario")
                                                intent.putExtra("PERFIL","$perfil")
                                                startActivity(intent)
                                            }
                                            Log.d(ContentValues.TAG, "DocumentSnapshot data: ${data}")
                                        } else {
                                            progress.visibility= View.INVISIBLE
                                            Log.d(ContentValues.TAG, "No such document")
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.d(ContentValues.TAG, "get failed with ", exception)
                                        progress.visibility= View.INVISIBLE
                                    }

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(ContentValues.TAG, "signInWithCustomToken:failure", task.exception)
                                Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
            catch (e:Exception){
                Toast.makeText(this, e.message,
                    Toast.LENGTH_SHORT).show()
            }
        }
        val boton_regresar=findViewById<Button>(R.id.btn_volver_log)
        boton_regresar.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}



