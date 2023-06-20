package org.bedu.movies_app_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.databinding.ActivityMainBinding

import org.bedu.movies_app_android.fragments.FragmentCinemaListings
import org.bedu.movies_app_android.fragments.FragmentFavorites

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater) }


    private val cinemaFragment = FragmentCinemaListings()

    private val favoritesFragment = FragmentFavorites()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        setCurrentFragment(cinemaFragment)
        createFragments()


    }

    private fun setCinemaFragment(): FragmentCinemaListings {
        val fragment = FragmentCinemaListings()
        /*fragment.setListener {
            Log.d("name", it.name)
        }*/

        return fragment
    }

    private fun createFragments(){
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_cinema_listings ->{
                    setCurrentFragment(cinemaFragment)
                    it.actionView?.clearFocus()
                    true
                }
                R.id.nav_favorite_movies ->{
                    setCurrentFragment(favoritesFragment)
                    it.actionView?.clearFocus()
                    true
                }

               /* R.id.nav_history->{
                    val args = Bundle()
                    val shared: SharedPreferences = getSharedPreferences("shared", MODE_PRIVATE)
                    args.putInt("idConductor", shared.getInt("idConductor",10))
                    args.putString("token", shared.getString("token","0"))
                    tercerFragment.arguments=args
                    // setCurrentFragment(tercerFragment)
                    // true
                    setCurrentFragment(tercerFragment)
                    it.actionView?.clearFocus()
                    true
                }*/
                else -> false
            }
        }
//        bottomNavigationView.getOrCreateBadge(R.id.nav_home).apply {
//            isVisible=true
//            number=8
//        }
    }
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerView,fragment)
            commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        /*// Manejar los eventos de los elementos de navegación aquí
        when (item.itemId) {
            R.id.nav_item1 -> showToast("Item 1 selected")
            R.id.nav_item2 -> showToast("Item 2 selected")
            R.id.nav_item3 -> showToast("Item 3 selected")
            // Agregar más casos para otros elementos de navegación
        }

        // Cerrar el drawer después de seleccionar un elemento
        drawerLayout.closeDrawer(GravityCompat.START)*/
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            // finish()
        }
    }
}