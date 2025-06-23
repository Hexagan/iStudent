package ar.com.ifts18.istudent

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var usuarioEditText: EditText
    private lateinit var contraseniaEditText: EditText
    private lateinit var recordarCheckBox: CheckBox
    private lateinit var ingresarButton: Button
    //private lateinit var registrarTextView: TextView
    private lateinit var invitadoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        usuarioEditText = findViewById(R.id.editTextUsuario)
        contraseniaEditText = findViewById(R.id.editTextContrasenia)
        recordarCheckBox = findViewById(R.id.checkboxRecordar)
        ingresarButton = findViewById(R.id.buttonIngresar)
        //registrarTextView = findViewById(R.id.textViewRegistrar)
        invitadoTextView = findViewById(R.id.textViewInvitado)

        ingresarButton.setOnClickListener {
            val usuario = usuarioEditText.text.toString()
            val contrasenia = contraseniaEditText.text.toString()

            if (usuario == "alumno" && contrasenia == "1234") {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

        /*
        registrarTextView.setOnClickListener {

            Toast.makeText(this, "Función de registro no implementada", Toast.LENGTH_SHORT).show()
        }
        */

        invitadoTextView.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }


    }
}
