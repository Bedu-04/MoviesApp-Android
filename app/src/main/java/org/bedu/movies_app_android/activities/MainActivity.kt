package org.bedu.movies_app_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.databinding.ActivityMainBinding

import org.bedu.movies_app_android.fragments.FragmentCinemaListings
import org.bedu.movies_app_android.fragments.FragmentFavorites

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recycler: FragmentContainerView

    val cinemaFragment = FragmentCinemaListings()

    val favortitesFragment = FragmentFavorites()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


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
                    setCurrentFragment(favortitesFragment)
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
}