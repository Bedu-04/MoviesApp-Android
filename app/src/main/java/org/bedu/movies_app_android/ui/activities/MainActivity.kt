package org.bedu.movies_app_android.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.databinding.ActivityMainBinding
import org.bedu.movies_app_android.store.Store
import org.bedu.movies_app_android.store.StoreSingleton
import org.bedu.movies_app_android.ui.fragments.FragmentCinemaListings
import org.bedu.movies_app_android.ui.fragments.FragmentFavorites
import org.bedu.movies_app_android.ui.fragments.NextToSeeFragment
import org.bedu.movies_app_android.ui.fragments.OnSearchListener
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),OnSearchListener ,NavigationView.OnNavigationItemSelectedListener {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater) }

    private val cinemaFragment = FragmentCinemaListings()

    private val favoritesFragment = FragmentFavorites()

    private val nextFragment = NextToSeeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        /*Search view*/
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                onSearch(query, false)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean = false

        })

        binding.searchView.setOnCloseListener {
            Log.d("Searchiview", "CLOSE")
            onSearch("", true)
            false
        }


        setCurrentFragment(cinemaFragment)
        createFragments()


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

                R.id.nav_see_letter_movies ->{
                    setCurrentFragment(nextFragment)
                    it.actionView?.clearFocus()
                    true
                }

                else -> false
            }
        }

    }
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerView,fragment)
            commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("DRAWER", item.toString())
        when(item.itemId){
            R.id.nav_cinema_listings ->{
                setCurrentFragment(cinemaFragment)
                item.actionView?.clearFocus()
                true
            }
            R.id.nav_favorite_movies ->{
                setCurrentFragment(favoritesFragment)
                item.actionView?.clearFocus()
                true
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
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

    override fun onSearch(query: String, resetMovies: Boolean) {
        // Envia el término de búsqueda al Fragmento
        val fragment = supportFragmentManager.findFragmentById(R.id.containerView) as? FragmentCinemaListings
        fragment?.onSearch(query, resetMovies)
    }


}