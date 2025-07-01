package ar.com.ifts18.istudent

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.core.content.edit

class RegistroFragment : Fragment(R.layout.fragment_registro) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val editTextUsuario = view.findViewById<EditText>(R.id.editTextUsuario)
        val editTextContrasenia = view.findViewById<EditText>(R.id.editTextContrasenia)
        val btnRegistrar = view.findViewById<Button>(R.id.btnRegistrar)

        val btnVolverLogin = view.findViewById<Button>(R.id.btnVolverLogin)
        btnVolverLogin.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, LoginFragment())
                addToBackStack(null)
            }
        }

        btnRegistrar.setOnClickListener {
            val usuario = editTextUsuario.text.toString()
            val contrasenia = editTextContrasenia.text.toString()
            val sharedPrefs = requireContext().getSharedPreferences("prefs_login", 0)

            if (usuario.isBlank() || contrasenia.isBlank()) {
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (sharedPrefs.contains("user_$usuario")) {
                Toast.makeText(requireContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show()
            } else {
                sharedPrefs.edit { putString("user_$usuario", contrasenia) }
                Toast.makeText(requireContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()

                parentFragmentManager.commit {
                    replace(R.id.fragment_container, LoginFragment())
                    addToBackStack(null)
                }
            }
        }
    }
}