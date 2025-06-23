package ar.com.ifts18.istudent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class AcercaDeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acerca_de)
        val btnVolver = findViewById<Button>(R.id.buttonVolver)

        btnVolver.setOnClickListener {
            startActivity(Intent(this, HomeFragment::class.java))
        }

    }
}