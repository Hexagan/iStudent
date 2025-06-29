package ar.com.ifts18.istudent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    private fun irA(fragment: Fragment): View.OnClickListener {
        return View.OnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
            (requireActivity() as MainActivity).mostrarNavBar()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCalificaciones = view.findViewById<LinearLayout>(R.id.buttonCalificaciones)
        val btnVencimientos = view.findViewById<LinearLayout>(R.id.buttonVencimientos)
        val btnPresentismo = view.findViewById<LinearLayout>(R.id.buttonPresentismo)
        val btnCronograma = view.findViewById<LinearLayout>(R.id.buttonCronograma)
        val btnAcercaDe = view.findViewById<LinearLayout>(R.id.buttonAcercaDe)
        val btnDesconectarse = view.findViewById<LinearLayout>(R.id.buttonDesconectarse)
        val btnCard1 = view.findViewById<LinearLayout>(R.id.buttonCard1)
        val btnCard2 = view.findViewById<LinearLayout>(R.id.buttonCard2)
        val btnCard3 = view.findViewById<LinearLayout>(R.id.buttonCard3)

        btnVencimientos.setOnClickListener (irA(VencimientosFragment()))
        btnCard1.setOnClickListener (irA(VencimientosFragment()))

        btnCalificaciones.setOnClickListener(irA(CalificacionesFragment()))
        btnCard2.setOnClickListener (irA(CalificacionesFragment()))

        btnPresentismo.setOnClickListener (irA(PresentismoFragment()))
        btnCard3.setOnClickListener (irA(PresentismoFragment()))

        btnCronograma.setOnClickListener (irA(CronogramaFragment()))

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
