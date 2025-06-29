package ar.com.ifts18.istudent

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PresentismoAdapter(private val materias: List<AsistenciaMateria>) :
    RecyclerView.Adapter<PresentismoAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCodigo: TextView = view.findViewById(R.id.tvCodigo)
        val tvAsistencia: TextView = view.findViewById(R.id.tvAsistencia)
        val tvFaltas: TextView = view.findViewById(R.id.tvFaltas)
        val tvPorcentaje: TextView = view.findViewById(R.id.tvPorcentaje)
        val tvEstado: TextView = view.findViewById(R.id.tvEstado)

        val viewAsistenciaColor: View = view.findViewById(R.id.viewAsistenciaColor)
        val viewFaltasColor: View = view.findViewById(R.id.viewFaltasColor)
        val viewPorcentajeColor: View = view.findViewById(R.id.viewPorcentajeColor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_presentismo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = materias.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val materia = materias[position]

        holder.tvCodigo.text = materia.codigo
        holder.tvAsistencia.text = materia.asistencia.toString()
        holder.tvFaltas.text = materia.faltas.toString()
        holder.tvPorcentaje.text = "${materia.porcentaje}%"
        holder.tvEstado.text = materia.estado

        // Colores de fondo de celdas
        holder.viewAsistenciaColor.setBackgroundColor(Color.parseColor("#A5D6A7")) // verde claro
        holder.viewFaltasColor.setBackgroundColor(Color.parseColor("#EF9A9A"))     // rojo claro

        val colorPorcentaje = when {
            materia.porcentaje >= 95 -> "#81C784" // verde
            materia.porcentaje >= 88 -> "#FFF176" // amarillo
            else -> "#FFB74D" // naranja
        }
        holder.viewPorcentajeColor.setBackgroundColor(Color.parseColor(colorPorcentaje))
    }
}