package ar.com.ifts18.istudent

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CronogramaFragment : Fragment(R.layout.fragment_cronograma) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCronograma)

        val horas = listOf("18:00", "20:00", "20:10", "22:00")

        val filas = listOf(
            listOf(R.color.azul, R.color.rojo, R.color.rojo, R.color.violeta, R.color.amarillo),
            listOf(R.color.azul, R.color.rojo, R.color.rojo, R.color.violeta, R.color.amarillo),
            listOf(R.color.azul, R.color.rojo, R.color.verde, R.color.amarillo, R.color.amarillo),
            listOf(R.color.azul, R.color.rojo, R.color.verde, R.color.amarillo, R.color.amarillo)
        )

        val listaCeldas = mutableListOf<Celda>()

        for ((index, fila) in filas.withIndex()) {
            listaCeldas.add(Celda.Hora(horas[index]))
            listaCeldas.addAll(fila.map {
                Celda.Materia(ContextCompat.getColor(requireContext(), it))
            })
        }

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 6)
        recyclerView.adapter = CronogramaAdapter(listaCeldas)
    }
}
