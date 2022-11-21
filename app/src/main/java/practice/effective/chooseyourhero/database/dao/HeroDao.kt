package practice.effective.chooseyourhero.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import practice.effective.chooseyourhero.models.Hero

@Dao
interface HeroDao {
    @Query("SELECT * FROM heroes")
    suspend fun getHeroesList(): List<Hero>

    @Query("SELECT * FROM heroes WHERE id LIKE :id")
    suspend fun getHero(id: String): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(hero: Hero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(heroes: List<Hero>)

    @Update
    fun updateHero(hero: Hero)

    @Update
    fun updateHeroes(heroes: List<Hero>)

    @Delete
    fun deleteOne(hero: Hero)

    @Delete
    fun deleteAll(heroes: List<Hero>)
}
