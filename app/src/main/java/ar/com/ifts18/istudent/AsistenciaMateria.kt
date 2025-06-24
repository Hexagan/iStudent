package ar.com.ifts18.istudent

data class AsistenciaMateria(
    val codigo: String, // Ej: "PRU"
    val asistencia: Int,
    val faltas: Int,
    val porcentaje: Int,
    val estado: String // Ej: "Reg"
)