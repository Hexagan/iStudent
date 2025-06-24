package ar.com.ifts18.istudent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PresentismoFragment : Fragment(R.layout.fragment_presentismo) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerPresentismo)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val listaMaterias = listOf(
            AsistenciaMateria("PRU", 24, 1, 96, "Reg"),
            AsistenciaMateria("MOB", 25, 0, 100, "Reg"),
            AsistenciaMateria("TIC", 8, 1, 88, "Reg"),
            AsistenciaMateria("COM", 8, 1, 88, "Reg"),
            AsistenciaMateria("PPI", 16, 3, 84, "Reg"),
        )

        recycler.adapter = PresentismoAdapter(listaMaterias)
    }
}