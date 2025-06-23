package ar.com.ifts18.istudent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        // Botones del menú principal
        val btnCalificaciones = findViewById<LinearLayout>(R.id.buttonCalificaciones)
        val btnVencimientos = findViewById<LinearLayout>(R.id.buttonVencimientos)
        val btnPresentismo = findViewById<LinearLayout>(R.id.buttonPresentismo)
        val btnCronograma = findViewById<LinearLayout>(R.id.buttonCronograma)
        val btnAcercaDe = findViewById<LinearLayout>(R.id.buttonAcercaDe)
        val btnDesconectarse = findViewById<LinearLayout>(R.id.buttonDesconectarse)

        btnCalificaciones.setOnClickListener {
            startActivity(Intent(this, CalificacionesActivity::class.java))
        }

        btnVencimientos.setOnClickListener {
            startActivity(Intent(this, VencimientosActivity::class.java))
        }

        btnPresentismo.setOnClickListener {
            startActivity(Intent(this, PresentismoActivity::class.java))
        }

        btnCronograma.setOnClickListener {
            startActivity(Intent(this, CronogramaActivity::class.java))
        }

        btnAcercaDe.setOnClickListener {
            startActivity(Intent(this, AcercaDeActivity::class.java))
        }

        btnDesconectarse.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}