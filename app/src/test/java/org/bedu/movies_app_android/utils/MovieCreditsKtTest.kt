package org.bedu.movies_app_android.utils

import org.bedu.movies_app_android.data.models.Cast
import org.bedu.movies_app_android.data.models.Crew
import org.junit.Assert.*
import org.junit.Test

class MovieCreditsKtTest{
    @Test
    fun getMovieCast_returnsZeroItem(){
        //Given
        val cast = listOf<Cast>(
            Cast("Jules", 1, "Acting", "Luciane Buchanan", "Luciane Buchanan", null),
            Cast("Ben", 2, "Acting", "Matt Whelan", "Matt Whelan", null),
            Cast("Jodie", 1, "Acting", "Jaya Beach-Robertson", "Jaya Beach-Robertson", null)
        )
        //When
        val result = getMovieCast(cast)

        //Then
        assertEquals(0, result.size)
    }


    @Test
    fun getMovieCast_returnsOneItem(){
        //Given
        val profilePath = "/qTIe7SblKwxR9DF6iNuVX0pEWIZ.jpg";
        val cast = listOf<Cast>(
            Cast("Jules", 1, "Acting", "Luciane Buchanan", "Luciane Buchanan", profilePath),
            Cast("Ben", 2, "Acting", "Matt Whelan", "Matt Whelan", null),
            Cast("Jodie", 3, "Acting", "Jaya Beach-Robertson", "Jaya Beach-Robertson", null)
        )
        //When
        val result = getMovieCast(cast)

        //Then
        assertEquals(1, result.size)

        assertEquals(profilePath, result[0].profile_path)
    }


    @Test
    fun getDirector_returnDommyProfilePath() {
        //Give
        val crew = listOf<Crew>(
            Crew(name = "Lesley Hansen", job = "Makeup Artist" ,profile_path = null),
            Crew(name = "Aaron Morton", job = "Executive Producer",profile_path = null),
            Crew(name = "Scott Walker", job = "Director",profile_path = null),
        )

        //When
        val result = getDirector(crew)

        //Then
        assertEquals("/yW6eiXF0CEXCHpqxqvEUZmq2mUq.jpg", result.profile_path)
    }


    @Test
    fun getDirector_returnProfilePath() {
        //Give
        val profilePath = "/ejgOlILvDF5NMKrTKPU4HeXOYNW.jpg";
        val crew = listOf<Crew>(
            Crew(name = "Lesley Hansen", job = "Makeup Artist" ,profile_path = null),
            Crew(name = "Aaron Morton", job = "Executive Producer",profile_path = null),
            Crew(name = "Scott Walker", job = "Director",profile_path = profilePath),
        )

        //When
        val result = getDirector(crew)

        //Then
        assertNotEquals("/yW6eiXF0CEXCHpqxqvEUZmq2mUq.jpg", profilePath)
        assertEquals(profilePath, result.profile_path)
        assertEquals("Scott Walker", result.name)
    }

}