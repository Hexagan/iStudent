package ar.com.ifts18.istudent

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PresentismoFragment : Fragment(R.layout.fragment_presentismo) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerPresentismo)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val decor = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        decor.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divisor_espacio)!!)
        recycler.addItemDecoration(decor)

        val listaMaterias = listOf(
            AsistenciaMateria("Pruebas de Sistema", 0, 100),
            AsistenciaMateria("Desarrollo Aplicaciones", 0, 100),
            AsistenciaMateria("Taller de Comunicación", 2, 90, listOf("30/03", "01/04")),
            AsistenciaMateria("Prácticas Profesionalizantes I", 3, 80, listOf("01/04", "08/04", "15/04")),
            AsistenciaMateria("Tecnologías de la Información", 5, 75, listOf("09/04", "16/04", "21/04", "23/04","28/04")),
        ).sortedByDescending { it.porcentaje }

        recycler.adapter = PresentismoAdapter(listaMaterias.toMutableList())
    }
}
