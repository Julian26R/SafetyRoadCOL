package com.ean.safetyroadcol

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(3000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        val db = Firebase.firestore
        val progress=findViewById<ProgressBar>(R.id.progressBar_ma)
        val boton_login=findViewById<Button>(R.id.BtnLogin_am)
        boton_login.setOnClickListener {
            progress.visibility= View.VISIBLE
            try{
                if(auth.currentUser!=null){
                    val email: String? = auth.currentUser!!.email

                    val docRef = db.collection("Usuario").document(email.toString())
                    docRef.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                val data=document.data
                                val usuario= document.get("correo")
                                val perfil= document.get("perfil")
                                if(perfil=="admin"){
                                    val intent= Intent(this,InicioAdmin::class.java)
                                    intent.putExtra("USUARIO","$usuario")
                                    intent.putExtra("PERFIL","$perfil")
                                    progress.visibility= View.INVISIBLE
                                    startActivity(intent)
                                }else{
                                    val intent= Intent(this,InicioUser::class.java)
                                    intent.putExtra("USUARIO","$usuario")
                                    intent.putExtra("PERFIL","$perfil")
                                    progress.visibility= View.INVISIBLE
                                    startActivity(intent)
                                }
                            } else {
                                Log.d(ContentValues.TAG, "No such document")
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.d(ContentValues.TAG, "get failed with ", exception)
                        }
                }else{
                    val intent= Intent(this,Login::class.java)
                    progress.visibility= View.INVISIBLE
                    startActivity(intent)
                }
            }catch (e:java.lang.Exception){
                val intent= Intent(this,Login::class.java)
                progress.visibility= View.INVISIBLE
                startActivity(intent)
            }
        }
    }
}