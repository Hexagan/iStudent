package ar.com.ifts18.istudent

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class LoginFragment : Fragment(R.layout.login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val usuarioEditText = view.findViewById<EditText>(R.id.editTextUsuario)
        val contraseniaEditText = view.findViewById<EditText>(R.id.editTextContrasenia)
        val recordarCheckBox = view.findViewById<CheckBox>(R.id.checkboxRecordar)
        val ingresarButton = view.findViewById<Button>(R.id.buttonIngresar)
        val invitadoTextView = view.findViewById<TextView>(R.id.textViewInvitado)

        ingresarButton.setOnClickListener {
            val usuario = usuarioEditText.text.toString()
            val contrasenia = contraseniaEditText.text.toString()

            if (usuario == "alumno" && contrasenia == "1234") {
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