package ar.com.ifts18.istudent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class AcercaDeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acerca_de)


        val btnTerminos = findViewById<Button>(R.id.buttonTerminos)

        btnTerminos.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data =
                "https://docs.github.com/en/site-policy/github-terms/github-terms-of-service".toUri()
            startActivity(intent)
        }
        val btnVolver = findViewById<Button>(R.id.buttonVolver)

        btnVolver.setOnClickListener {
            finish()
        }

    }
}