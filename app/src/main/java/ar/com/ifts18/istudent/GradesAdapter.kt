package ar.com.ifts18.istudent

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class GradesAdapter(private val gradeList: List<Calificacion>) :
    RecyclerView.Adapter<GradesAdapter.GradeViewHolder>() {

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
        holder.promedio.text = String.format(Locale.getDefault(), "%.2f", currentItem.promedio)

        // Lógica para el color de fondo del promedio
        val average = currentItem.promedio
        val backgroundColor: Int = when {
            average >= 7.0 -> Color.parseColor("#00DAF4")
            average >= 6.0 -> Color.parseColor("#00FF0A")
            average >= 4.0 -> Color.parseColor("#FFEB3B")
            else -> Color.parseColor("#E2515D")
        }
        holder.promedio.background = ColorDrawable(backgroundColor)


    }

    override fun getItemCount() = gradeList.size
}