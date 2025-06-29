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

        bottomNav.visibility = View.GONE

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
        }
        setupNavigation()

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

    fun mostrarNavBar() {
        bottomNav.visibility = View.VISIBLE
    }

    fun ocultarNavBar() {
        bottomNav.visibility = View.GONE
    }

}