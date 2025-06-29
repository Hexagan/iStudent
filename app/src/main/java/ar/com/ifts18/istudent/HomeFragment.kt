package ar.com.ifts18.istudent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCalificaciones = view.findViewById<LinearLayout>(R.id.buttonCalificaciones)
        val btnVencimientos = view.findViewById<LinearLayout>(R.id.buttonVencimientos)
        val btnPresentismo = view.findViewById<LinearLayout>(R.id.buttonPresentismo)
        val btnCronograma = view.findViewById<LinearLayout>(R.id.buttonCronograma)
        val btnAcercaDe = view.findViewById<LinearLayout>(R.id.buttonAcercaDe)
        val btnDesconectarse = view.findViewById<LinearLayout>(R.id.buttonDesconectarse)

        btnCalificaciones.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CalificacionesFragment())
                .addToBackStack(null)
                .commit()

            (requireActivity() as MainActivity).mostrarNavBar()
        }

        btnVencimientos.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, VencimientosFragment())
                .addToBackStack(null)
                .commit()

            (requireActivity() as MainActivity).mostrarNavBar()
        }

        btnPresentismo.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PresentismoFragment())
                .addToBackStack(null)
                .commit()

            (requireActivity() as MainActivity).mostrarNavBar()
        }

        btnCronograma.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CronogramaFragment())
                .addToBackStack(null)
                .commit()

            (requireActivity() as MainActivity).mostrarNavBar()
        }

        btnAcercaDe.setOnClickListener {
            startActivity(Intent(requireContext(), AcercaDeActivity::class.java))
        }

        btnDesconectarse.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            requireActivity().finish()
            (requireActivity() as MainActivity).ocultarNavBar()
        }
    }
}
