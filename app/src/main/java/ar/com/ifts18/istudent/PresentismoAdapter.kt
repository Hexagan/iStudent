package ar.com.ifts18.istudent

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt

data class AsistenciaMateria(
    val nombre: String,
    val faltas: Int,
    val porcentaje: Int,
    val ausencias: List<String> = emptyList(),
    var expandido: Boolean = false
)
class PresentismoAdapter(private val materias: MutableList<AsistenciaMateria>) :
    RecyclerView.Adapter<PresentismoAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMateria: TextView = view.findViewById(R.id.tvMateria)
        val tvPorcentaje: TextView = view.findViewById(R.id.tvPorcentaje)
        val tvCantidadFaltas: TextView = view.findViewById(R.id.tvCantidadFaltas)
        val layoutAusencias: LinearLayout = view.findViewById(R.id.layoutAusencias)
        val tvDiasAusentes: TextView = view.findViewById(R.id.tvDiasAusentes)
        val btnExpand: ImageButton = view.findViewById(R.id.btnExpand)
        val seccionExpandir: LinearLayout = view.findViewById(R.id.seccionExpandir)
        val iconMateria: ImageView = view.findViewById(R.id.iconMateria)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_presentismo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = materias.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val materia = materias[position]
        val card = holder.itemView.findViewById<CardView>(R.id.cardRoot)

        holder.tvMateria.text = materia.nombre
        holder.tvPorcentaje.text = "${materia.porcentaje}%"
        holder.tvCantidadFaltas.text = materia.faltas.toString()
        holder.tvDiasAusentes.text = materia.ausencias.joinToString(", ")

        val faltaColor = when {
            materia.porcentaje >= 98 -> "#388E3C"
            materia.porcentaje >= 90 -> "#689F38"
            materia.porcentaje >= 85 -> "#FBC02D"
            materia.porcentaje >= 80 -> "#F57C00"
            else -> "#C62828"
        }
        holder.tvCantidadFaltas.setBackgroundColor(faltaColor.toColorInt())

        val bgColor = when {
            materia.porcentaje >= 98 -> "#63b36d"
            materia.porcentaje >= 90 -> "#DCE775"
            materia.porcentaje >= 85 -> "#FFF176"
            materia.porcentaje >= 80 -> "#FFB74D"
            else -> "#E57373"
        }
        card.setCardBackgroundColor(bgColor.toColorInt())

        val iconRes = when (materia.nombre) {
            "Metodología de Pruebas de Sistema" -> R.drawable.ic_metodologia
            "Desarrollo de Aplicaciones Móviles" -> R.drawable.ic_mobile
            "Taller de Comunicación" -> R.drawable.ic_comunicacion
            "Prácticas Profesionalizantes I" -> R.drawable.ic_ppi
            "Tecnologías de la Información" -> R.drawable.ic_ti
            else -> R.drawable.ic_school
        }

        holder.iconMateria.setImageResource(iconRes)
        holder.iconMateria.setColorFilter(Color.WHITE) // para tint blanco

        // Expandir solo si hay ausencias
        if (materia.ausencias.isNotEmpty()) {
            holder.seccionExpandir.visibility = View.VISIBLE
            holder.btnExpand.setImageResource(
                if (materia.expandido) R.drawable.ic_expandir_menos else R.drawable.ic_expandir_mas
            )

            holder.layoutAusencias.visibility =
                if (materia.expandido) View.VISIBLE else View.GONE

            holder.btnExpand.setOnClickListener {
                materia.expandido = !materia.expandido
                notifyItemChanged(position)
            }
        } else {
            holder.seccionExpandir.visibility = View.GONE
            holder.layoutAusencias.visibility = View.GONE
        }
    }
}


