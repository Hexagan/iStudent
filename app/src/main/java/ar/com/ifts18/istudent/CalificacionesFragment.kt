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
            Calificacion("PRU", 9, 6, 5, 10, 7.5), //
            Calificacion("MOB", 7, 5, 6, 6, 6.0), //
            Calificacion("TIC", 8, 6, 9, 7, 7.5), //
            Calificacion("COM", 8, 7, 4, 10, 7.25), //
            Calificacion("PPI", 10, 0, 2, 5, 4.25) // Ejemplo con promedio bajo para probar color "Libre"
        )
        Log.d("DEBUG_APP", "generateDummyData() retornando ${dummyData.size} items.") //
        return dummyData //
    }
}