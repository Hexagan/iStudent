package ar.com.ifts18.istudent


import android.graphics.drawable.LayerDrawable
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale // formato de promedio
import androidx.core.graphics.toColorInt
import androidx.core.graphics.drawable.toDrawable

class CalificacionesAdapter(private val gradeList: List<Calificacion>) :
    RecyclerView.Adapter<CalificacionesAdapter.GradeViewHolder>() {

    inner class GradeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val materia: TextView = itemView.findViewById(R.id.materia) //
        val pa1: TextView = itemView.findViewById(R.id.pa1) //
        val tp1: TextView = itemView.findViewById(R.id.tp1) //
        val pa2: TextView = itemView.findViewById(R.id.pa2) //
        val tp2: TextView = itemView.findViewById(R.id.tp2) //
        val promedio: TextView = itemView.findViewById(R.id.prom) //
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder { //
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calification_row, parent, false) //
        return GradeViewHolder(view) //
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) { //
        val currentItem = gradeList[position] //

        holder.materia.text = currentItem.materia //
        holder.pa1.text = currentItem.pa1.toString() //
        holder.tp1.text = currentItem.tp1.toString() //
        holder.pa2.text = currentItem.pa2.toString() //
        holder.tp2.text = currentItem.tp2.toString() //
        // Formatear el promedio a dos decimales
        holder.promedio.text = String.format(Locale.getDefault(), "%.2f", currentItem.promedio) // Formato a 2 decimales

        // Lógica para el color de fondo del promedio
        val average = currentItem.promedio //
        val backgroundColor: Int = when { //
            average >= 7.0 -> "#00DAF4".toColorInt()
            average >= 6.0 && average < 7.0 -> "#00FF0A".toColorInt()
            average >= 4.0 -> "#FFEB3B".toColorInt()
            else -> "#D52337".toColorInt()
        }

        // Crear un LayerDrawable: el borde abajo, el color de fondo encima
        val borderDrawable = ContextCompat.getDrawable(holder.itemView.context, R.drawable.borde_celda) // Asegúrate de que este drawable exista
        val colorDrawable = backgroundColor.toDrawable()
        val layerDrawable = LayerDrawable(arrayOf(borderDrawable, colorDrawable))

        // Ajustar los insets (margen) del colorDrawable para que el borde sea visible
        // Esto crea un pequeño margen (1dp en cada lado) para que el borde subyacente se vea.
        layerDrawable.setLayerInset(1, // Índice del colorDrawable en el array (el segundo elemento)
            1, // left (1dp)
            1, // top (1dp)
            1, // right (1dp)
            1  // bottom (1dp)
        )

        holder.promedio.background = layerDrawable // Asignar el LayerDrawable al background
    }

    override fun getItemCount() = gradeList.size //
}