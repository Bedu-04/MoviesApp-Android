package org.bedu.movies_app_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler)

        recycler.adapter =  RecyclerMovieAdapter(listOf(
            Movie(
                1,
                "Scream 6",
                listOf("Pedro Guzman"),
                123.0,
                listOf("Joaquin Lopez Doriga"),
                "9-03-2023",
                7.2,
                Language.Español,
                Category.Suspenso
            ),
            Movie(
                1,
                "Scream 6",
                listOf("Pedro Guzman"),
                123.0,
                listOf("Joaquin Lopez Doriga"),
                "9-03-2023",
                7.2,
                Language.Español,
                Category.Suspenso
            ),
            Movie(
                1,
                "Scream 6",
                listOf("Pedro Guzman"),
                123.0,
                listOf("Joaquin Lopez Doriga"),
                "9-03-2023",
                7.2,
                Language.Español,
                Category.Suspenso
            ),
            Movie(
                1,
                "Scream 6",
                listOf("Pedro Guzman"),
                123.0,
                listOf("Joaquin Lopez Doriga"),
                "9-03-2023",
                7.2,
                Language.Español,
                Category.Suspenso
            ),            Movie(
                1,
                "Scream 6",
                listOf("Pedro Guzman"),
                123.0,
                listOf("Joaquin Lopez Doriga"),
                "9-03-2023",
                7.2,
                Language.Español,
                Category.Suspenso
            )
        ))

    }
}