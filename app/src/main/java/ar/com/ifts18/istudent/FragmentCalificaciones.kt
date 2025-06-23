package ar.com.ifts18.istudent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CalificacionesAdapter(private val lista: List<Calificacion>) :
    RecyclerView.Adapter<CalificacionesAdapter.CalificacionViewHolder>() {

    class CalificacionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val materia: TextView = view.findViewById(R.id.materia)
        val pa1: TextView = view.findViewById(R.id.pa1)
        val tp1: TextView = view.findViewById(R.id.tp1)
        val pa2: TextView = view.findViewById(R.id.pa2)
        val tp2: TextView = view.findViewById(R.id.tp2)
        val prom: TextView = view.findViewById(R.id.prom)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalificacionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calificaciones, parent, false)
        return CalificacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalificacionViewHolder, position: Int) {
        val calificacion = lista[position]

        holder.materia.text = calificacion.materia
        holder.pa1.text = calificacion.pa1.toString()
        holder.tp1.text = calificacion.tp1.toString()
        holder.pa2.text = calificacion.pa2.toString()
        holder.tp2.text = calificacion.tp2.toString()
        holder.prom.text = String.format("%.2f", calificacion.promedio)

        // Coloreado del promedio según el rango
        val color = when {
            calificacion.promedio >= 7 -> R.color.azul_promocionado      // Celeste
            calificacion.promedio >= 6 -> R.color.verde_zona_promocion   // Verde
            calificacion.promedio >= 4 -> R.color.violeta_regular        // Violeta
            else -> R.color.rojo_libre                                   // Rojo
        }
        holder.prom.setBackgroundResource(color)
    }

    override fun getItemCount(): Int = lista.size
}