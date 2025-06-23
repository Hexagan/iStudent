package ar.com.ifts18.istudent

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_navigation)

        // Ocultar nav bar al inicio (pantalla de login)
        bottomNav.visibility = View.GONE

        // Mostrar LoginFragment al iniciar la app
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
        }

        // Configurar navegación global
        setupNavigation()

    }

    fun mostrarNavBar() {
        bottomNav.visibility = View.VISIBLE
    }

    private fun setupNavigation() {
        bottomNav.setOnItemSelectedListener { item ->
            bottomNav.visibility = View.VISIBLE
            val fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_calificaciones -> CalificacionesFragment()
                R.id.nav_cronograma -> CronogramaFragment()
                R.id.nav_vencimientos -> VencimientosFragment()
                R.id.nav_presentismo -> PresentismoFragment()
                else -> null
            }

            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commit()
                true
            } ?: false
        }

    }

    /**
     * Método para activar la barra y abrir el fragment inicial tras login
     */
    fun navegarAPantallaPrincipal() {
        bottomNav.visibility = View.GONE
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()
    }

    fun cerrarSesion() {
        bottomNav.visibility = View.GONE
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment())
            .commit()
    }
}