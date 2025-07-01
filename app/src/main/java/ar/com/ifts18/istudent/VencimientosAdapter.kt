package ar.com.ifts18.istudent

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale
import androidx.core.graphics.toColorInt

data class MateriaVencimiento(
    val nombre: String,
    val fechaTPStr: String,
    val fechaParcialStr: String
)
class VencimientosAdapter(
    private val materias: List<MateriaVencimiento>,
    private val onRecordatorio: (String, String, Long) -> Unit
) : RecyclerView.Adapter<VencimientosAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombreMateria: TextView = view.findViewById(R.id.tvNombreMateria)
        val btnNotificacion: ImageButton = view.findViewById(R.id.btnNotificacion)
        val tvFechaTP: TextView = view.findViewById(R.id.tvFechaTP)
        val tvEstadoTP: TextView = view.findViewById(R.id.tvEstadoTP)
        val tvFechaParcial: TextView = view.findViewById(R.id.tvFechaParcial)
        val tvEstadoParcial: TextView = view.findViewById(R.id.tvEstadoParcial)
        val iconMateria: ImageView = view.findViewById(R.id.iconMateria)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vencimientos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = materias.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val materia = materias[position]
        holder.tvNombreMateria.text = materia.nombre

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())
        val today = LocalDate.now()

        // === materia ===
        val cardRoot = holder.itemView.findViewById<CardView>(R.id.cardRoot)
        val bgColor = when (materia.nombre) {
            "Metodología de Pruebas de Sistema" -> "#A9C47F"
            "Taller de Comunicación" -> "#8DA6BF"
            "Desarrollo de Aplicaciones Móviles" -> "#388E8E"
            "Tecnologías de la Información" -> "#B39261"
            "PPI", "Prácticas Profesionalizantes I" -> "#B38FBF"
            else -> "#ECECEC"
        }
        cardRoot.setCardBackgroundColor(bgColor.toColorInt())

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

        // === TP ===
        holder.tvFechaTP.text = materia.fechaTPStr
        if (materia.fechaTPStr.trim() == "-" || materia.fechaTPStr.trim().isEmpty()) {
            holder.tvEstadoTP.text = "Entregado"
            holder.tvEstadoTP.setTextColor("#4CAF50".toColorInt())
            holder.tvFechaTP.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_fecha_generica)
        } else {
            val fechaTP = runCatching { LocalDate.parse(materia.fechaTPStr, formatter) }.getOrNull()
            if (fechaTP != null) {
                when {
                    fechaTP.isBefore(today) -> {
                        holder.tvEstadoTP.text = "Vencido"
                        holder.tvEstadoTP.setTextColor("#888888".toColorInt())
                        holder.tvFechaTP.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_fecha_vencida)
                    }
                    else -> {
                        val dias = ChronoUnit.DAYS.between(today, fechaTP)
                        holder.tvEstadoTP.text = "$dias días restantes"
                        holder.tvEstadoTP.setTextColor("#888888".toColorInt())
                        holder.tvFechaTP.background = if (dias in 1..3)
                            ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_fecha_proxima)
                        else
                            ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_fecha_generica)
                    }
                }
                holder.tvFechaTP.setOnClickListener {
                    val millis = fechaTP.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    onRecordatorio(materia.nombre, "TP", millis)
                }
            }
        }

        // === Parcial ===
        holder.tvFechaParcial.text = materia.fechaParcialStr
        if (materia.fechaParcialStr.trim() == "-" || materia.fechaParcialStr.trim().isEmpty()) {
            holder.tvEstadoParcial.text = "Completado"
            holder.tvEstadoParcial.setTextColor("#4CAF50".toColorInt())
            holder.tvFechaParcial.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_fecha_generica)
        } else {
            val fechaParcial = runCatching { LocalDate.parse(materia.fechaParcialStr, formatter) }.getOrNull()
            if (fechaParcial != null) {
                val dias = ChronoUnit.DAYS.between(today, fechaParcial)
                holder.tvEstadoParcial.text = "$dias días restantes"
                holder.tvEstadoParcial.setTextColor("#888888".toColorInt())
                holder.tvFechaParcial.background = if (dias in 1..3)
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_fecha_proxima)
                else
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_fecha_generica)

                holder.tvFechaParcial.setOnClickListener {
                    val millis = fechaParcial.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    onRecordatorio(materia.nombre, "Parcial", millis)
                }
            }
        }

        // Botón de notificación activa recordatorios para ambos
        holder.btnNotificacion.setOnClickListener {
            val fechaTP = runCatching { LocalDate.parse(materia.fechaTPStr, formatter) }.getOrNull()
            if (fechaTP != null) {
                val millis = fechaTP.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                onRecordatorio(materia.nombre, "TP", millis)
            }
            val fechaParcial = runCatching { LocalDate.parse(materia.fechaParcialStr, formatter) }.getOrNull()
            if (fechaParcial != null) {
                val millis = fechaParcial.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                onRecordatorio(materia.nombre, "Parcial", millis)
            }
            holder.btnNotificacion.setImageResource(R.drawable.ic_campana_activado)
        }
    }
}


