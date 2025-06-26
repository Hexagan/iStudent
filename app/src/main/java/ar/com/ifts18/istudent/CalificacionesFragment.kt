package ar.com.ifts18.istudent

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class CalificacionesFragment : Fragment(R.layout.fragment_calificaciones) { //

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //
        super.onViewCreated(view, savedInstanceState)

        // 1. Encontrar el RecyclerView en el layout del Fragment
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewCalificaciones) //

        // 2. Configurar el LayoutManager (si no lo hiciste en el XML con app:layoutManager)
        recyclerView.layoutManager = LinearLayoutManager(view.context) //

        // 3. Generar los datos de ejemplo
        val gradeList = generateDummyData() //
        Log.d("DEBUG_APP", "Tamaño de gradeList en CalificacionesFragment: ${gradeList.size}") // Agregado para depuración

        // 4. Inicializar y asignar el adaptador al RecyclerView
        val gradesAdapter = GradesAdapter(gradeList) //
        recyclerView.adapter = gradesAdapter //
    }

    // Función para generar los datos de prueba (MOVIDA AQUÍ)
    private fun generateDummyData(): List<Calificacion> { //
        val dummyData = listOf( //
            Calificacion("Prueba de Sistemas", 9, 6, 5, 10, 7.5), //
            Calificacion("Desarrollo Mobile", 7, 5, 6, 6, 6.0), //
            Calificacion("Tecnologias de la Información", 9, 1, 5, 2, 4.25), //
            Calificacion("Taller de Comunicación", 8, 7, 4, 10, 7.25), //
            Calificacion("Práctica Profesional I", 7, 1, 2, 5, 3.75) // Ejemplo con promedio bajo para probar color "Libre"
        )
        Log.d("DEBUG_APP", "generateDummyData() retornando ${dummyData.size} items.") //
        return dummyData //
    }
}