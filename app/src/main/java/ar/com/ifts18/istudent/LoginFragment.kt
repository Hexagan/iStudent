package ar.com.ifts18.istudent

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.core.content.edit

class LoginFragment : Fragment(R.layout.login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val usuarioEditText = view.findViewById<EditText>(R.id.editTextUsuario)
        val contraseniaEditText = view.findViewById<EditText>(R.id.editTextContrasenia)
        val recordarCheckBox = view.findViewById<CheckBox>(R.id.checkboxRecordar)
        val ingresarButton = view.findViewById<Button>(R.id.buttonIngresar)
        val invitadoTextView = view.findViewById<TextView>(R.id.textViewInvitado)

        val sharedPrefs = requireContext().getSharedPreferences("prefs_login", 0)
        val recordar = sharedPrefs.getBoolean("recordar", false)
        if (recordar) {
            val usuarioGuardado = sharedPrefs.getString("usuario", "")
            val passGuardado = sharedPrefs.getString("password", "")
            usuarioEditText.setText(usuarioGuardado)
            contraseniaEditText.setText(passGuardado)
            recordarCheckBox.isChecked = true
        }

        ingresarButton.setOnClickListener {
            val usuario = usuarioEditText.text.toString()
            val contrasenia = contraseniaEditText.text.toString()

            if (usuario == "alumno" && contrasenia == "1234") {
                sharedPrefs.edit {
                    if (recordarCheckBox.isChecked) {
                        putString("usuario", usuario)
                        putString("password", contrasenia)
                        putBoolean("recordar", true)
                    } else {
                        clear()
                    }
                }
                parentFragmentManager.commit {
                    replace(R.id.fragment_container, HomeFragment())
                    addToBackStack(null)
                    (requireActivity() as MainActivity).ocultarNavBar()
                }
            } else {
                Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

        invitadoTextView.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, HomeFragment())
                addToBackStack(null)
            }
        }
    }
}