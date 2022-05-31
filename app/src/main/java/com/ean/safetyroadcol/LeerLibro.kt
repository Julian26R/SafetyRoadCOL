package com.ean.safetyroadcol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.github.barteksc.pdfviewer.PDFView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class LeerLibro : AppCompatActivity() {
    val storage = Firebase.storage
    val ONE_MEGABYTE:Long = 1024 * 1024*90
    private final var TAG = "leer_pdf"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leer_libro)

        var nombre_libro = intent.getStringExtra("TITULO LIBRO").toString()
        var proceesbar = findViewById<ProgressBar>(R.id.progressBar)
        var pdf = findViewById<PDFView>(R.id.pdfview)
        val Ref = storage.reference.child("vehiculo")
        Log.d("Firebase","Files $nombre_libro")
        Ref.child(nombre_libro).getBytes(ONE_MEGABYTE).addOnSuccessListener{
            pdf.fromBytes(it).load()
            proceesbar.visibility= View.INVISIBLE
        }.addOnFailureListener{
            Toast.makeText(this,"No se puedo descargar el soat: $nombre_libro",Toast.LENGTH_SHORT).show()
        }
    }
}