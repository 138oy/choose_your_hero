package practice.effective.chooseyourhero.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import practice.effective.chooseyourhero.models.Hero

@Dao
interface HeroDao {
    @Query("SELECT * FROM heroes")
    fun getHeroesList(): List<Hero>

    @Query("SELECT * FROM heroes WHERE id LIKE :id")
    fun getHeroById(id: String): Hero

    @Insert
    fun insertOne(hero: Hero)

    @Insert
    fun insertAll(heroes: List<Hero>)

    @Update
    fun updateHero(hero: Hero)

    @Update
    fun updateHeroes(heroes: List<Hero>)

    @Delete
    fun deleteOne(hero: Hero)

    @Delete
    fun deleteAll(heroes: List<Hero>)
}
