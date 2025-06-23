package ar.com.ifts18.istudent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class CalificacionesFragment : Fragment(R.layout.fragment_calificaciones) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Acá hacés el setup de la UI si es necesario
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCalificaciones)
        // Configurar RecyclerView, adaptadores, etc.

        // Acá podés acceder a elementos de la vista con: view.findViewById(...)
        // Por ejemplo:
        // val texto = view.findViewById<TextView>(R.id.tu_textview)
        // texto.text = "¡Hola desde el fragment!"
    }
}