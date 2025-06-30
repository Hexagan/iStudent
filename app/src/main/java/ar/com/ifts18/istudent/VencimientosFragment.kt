package ar.com.ifts18.istudent

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VencimientosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vencimientos, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerVencimientos)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val listaMaterias = listOf(
            MateriaVencimiento("Metodología de Pruebas de Sistema", "01/07/2025", "10/07/2025"),
            MateriaVencimiento("Desarrollo de Aplicaciones Móviles", "07/07/2025", "-"),
            MateriaVencimiento("Taller de Comunicación", "-", "05/07/2025"),
            MateriaVencimiento("Prácticas Profesionalizantes I", "07/07/2025", "08/07/2025"),
            MateriaVencimiento("Tecnologías de la Información", "-", "-")
        )

        recycler.adapter = VencimientosAdapter(listaMaterias) { materia, tipo, fechaMillis ->
            agregarEventoCalendario(materia, tipo, fechaMillis)
        }

        return view
    }

    private fun agregarEventoCalendario(materia: String, tipo: String, fechaMillis: Long) {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, "$tipo - $materia")
            putExtra(CalendarContract.Events.DESCRIPTION, "Recordatorio para $tipo de $materia")
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, fechaMillis)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, fechaMillis + 60 * 60 * 1000) // 1 hora
        }

        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "No se pudo abrir el calendario", Toast.LENGTH_SHORT).show()
        }
    }
}
