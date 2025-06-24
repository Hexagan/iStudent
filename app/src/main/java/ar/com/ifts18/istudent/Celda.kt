package ar.com.ifts18.istudent

sealed class Celda {
    data class Hora(val texto: String) : Celda()
    data class Materia(val color: Int) : Celda()
}