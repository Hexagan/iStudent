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
        val registrarTextView = view.findViewById<TextView>(R.id.textViewRegistrar)

        val sharedPrefs = requireContext().getSharedPreferences("prefs_login", 0)
        val recordar = sharedPrefs.getBoolean("recordar", false)

        if (recordar) {
            val lastUser = sharedPrefs.getString("last_user", "")
            usuarioEditText.setText(lastUser)


            val pass = sharedPrefs.getString("user_$lastUser", "")
            contraseniaEditText.setText(pass)

            recordarCheckBox.isChecked = true
        }

        ingresarButton.setOnClickListener {
            val usuario = usuarioEditText.text.toString()
            val contrasenia = contraseniaEditText.text.toString()
            val passRegistrada = sharedPrefs.getString("user_$usuario", null)

            if (passRegistrada != null && passRegistrada == contrasenia) {
                sharedPrefs.edit {
                    putString("last_user", usuario)

                    if (recordarCheckBox.isChecked) { putBoolean("recordar", true) }
                    else { putBoolean("recordar", false) }
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

        registrarTextView.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, RegistroFragment())
                addToBackStack(null)
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