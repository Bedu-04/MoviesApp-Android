package org.bedu.movies_app_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.databinding.ActivityMainBinding
import org.bedu.movies_app_android.fragments.FragmentCinemaListings
import org.bedu.movies_app_android.fragments.FragmentFavorites

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val cinemaFragment = FragmentCinemaListings()
    val favortitesFragment = FragmentFavorites()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCurrentFragment(cinemaFragment)
        createFragments()
        /*recycler.adapter =  RecyclerMovieAdapter(listOf(
            Movie(
                1,
                "John Wick 4",
                listOf("Keanu Reeves", "Bill Skarsgård", "Ian McShane", "Laurence Fishburne"),
                listOf("Chad Stahelski"),
                169.0,
                "9-03-2023",
                9.2,
                Language.Francés,
                Category.Suspenso,
                "John Wick (Keanu Reeves) descubre un camino para derrotar a La Mesa. Pero antes de poder ganar su libertad, Wick deberá enfrentarse a un nuevo enemigo con poderosas alianzas en todo el mundo; y contra las fuerzas que convierten a viejos amigos en enemigos.",
                R.drawable.scream_6_poster
            ),
            Movie(
                2,
                "Guardianes de la galaxia",
                listOf("Dave Bautista", "Chris Pratt", "Zoe Saldaña", " Bradley Cooper"),
                listOf("James Gunn"),
                149.0,
                "9-05-2023",
                7.9,
                Language.Español,
                Category.CienciaFicción,
                "En GUARDIANES DE LA GALAXIA VOL. 3 de Marvel Studios, la querida banda de Guardianes se instala en Knowhere. Pero sus vidas no tardan en verse alteradas por los ecos del turbulento pasado de Rocket. Peter Quill, aún conmocionado por la pérdida de Gamora, debe reunir a su equipo en una peligrosa misión para salvar la vida de Rocket, una misión que, si no se completa con éxito, podría muy posiblemente conducir al final de los Guardianes tal y como los conocemos.",
                R.drawable.guardians_of_the_galaxy_poster
            ),
            Movie(
                3,
                "Rapidos y furiosos 10",
                listOf("Vin Diesel","Jason Statham", "John Cena", "Scott Eastwood"),
                listOf("Louis Leterrier"),
                123.0,
                "9-03-2023",
                8.4,
                Language.Español,
                Category.Acción,
                "En 2011, Rápidos y Furiosos 5in Control, Dom y su equipo derrotaron al infame capo brasileño Hernán Reyes y decapitaron su imperio en un puente en Río de Janeiro. Lo que no sabían era que el hijo de Reyes, Dante (Jason Momoa, Aquaman), fue testigo de todo y ha pasado los últimos 12 años ideando un plan para hacer que Dom pague el precio más alto",
                R.drawable.fast_and_furious_x_poster
            ),
            Movie(
                4,
                "Los Caballeros del Zodiaco: Saint Seiya El Inicio",
                listOf("Madison Iseman", "Mackenyu", "Famke Janssen", "Sean Bean"),
                listOf("Tomek Baginski"),
                112.0,
                "9-03-2023",
                9.6,
                Language.Coreano,
                Category.CienciaFicción,
                "Cuando un testarudo huérfano de la calle, Seiya (Mackenyu), en busca de su hermana secuestrada, sin saberlo aprovecha poderes ocultos, descubre que podría ser la única persona viva que puede proteger a una diosa reencarnada, enviada para velar por la humanidad.",
                R.drawable.knights_of_the_zodiac_poster
            ),
            Movie(
                6,
                "Evil Dead: El Despertar",
                listOf("Alyssa Sutherland", "Lily Sullivan"),
                listOf("Lee Cronin"),
                123.0,
                "9-03-2023",
                7.2,
                Language.Inglés,
                Category.Terror,
                "Trasladando la acción fuera del bosque a la ciudad, “Evil Dead: El despertar” cuenta una historia retorcida de dos hermanas separadas, interpretadas por Sutherland y Sullivan, cuya reunión se ve interrumpida por el surgimiento de demonios que poseen, arrojándolas a un batalla primordial por la supervivencia mientras se enfrentan a la versión más pesadillesca de la familia imaginable.",
                R.drawable.evil_dead_poster
            ),
            Movie(
                7,
                "Super Mario Bros. La Película",
                listOf("Michel Jelenic"),
                listOf("Aaron Horvath"),
                92.0,
                "9-03-2023",
                9.7,
                Language.Francés,
                Category.Fantasía,
                "Un fontanero llamado Mario viaja a través de un laberinto subterráneo con su hermano, Luigi, tratando de salvar a una princesa capturada. Adaptación cinematográfica del popular videojuego.",
                R.drawable.super_mario_poster
            ),
            Movie(
                8,
                "Sundown: Secretos en Acapulco",
                listOf("Charlotte Gainsbourg", "Henry Goodman", "Iazua Larios", "Tim Roth"),
                listOf("Michel Franco"),
                92.0,
                "9-03-2023",
                9.7,
                Language.Francés,
                Category.Drama,
                "Una familia inglesa disfruta de unas vacaciones de lujo en Acapulco. El placer se ve interrumpido por la muerte de la abuela, deben volver a Londres en el primer vuelo. Neil (interpretado por Tim Roth) decide escapar de la familia y perder el vuelo, quedándose en Acapulco de manera indefinida. ¿Lo hace para quedarse con la herencia? ¿Qué secretos esconde?",
                R.drawable.super_mario_poster
            ),
        ))*/

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