package ar.com.ifts18.istudent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CronogramaAdapter(private val celdas: List<Celda>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HORA = 0
        private const val TYPE_MATERIA = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (celdas[position]) {
            is Celda.Hora -> TYPE_HORA
            is Celda.Materia -> TYPE_MATERIA
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HORA -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_celda_hora, parent, false)
                HoraViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_celda_cronograma, parent, false)
                MateriaViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = celdas[position]) {
            is Celda.Hora -> (holder as HoraViewHolder).textHora.text = item.texto
            is Celda.Materia -> (holder as MateriaViewHolder).colorView.setBackgroundColor(item.color)
        }
    }

    override fun getItemCount(): Int = celdas.size

    class HoraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textHora: TextView = itemView.findViewById(R.id.textHora)
    }

    class MateriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorView: View = itemView.findViewById(R.id.colorView)
    }
}