package org.bedu.movies_app_android.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
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

import org.bedu.movies_app_android.ui.fragments.FragmentCinemaListings
import org.bedu.movies_app_android.ui.fragments.FragmentFavorites

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

}