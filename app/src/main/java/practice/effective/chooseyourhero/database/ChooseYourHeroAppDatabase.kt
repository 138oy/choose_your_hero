package practice.effective.chooseyourhero.database

import androidx.room.Database
import androidx.room.RoomDatabase
import practice.effective.chooseyourhero.database.dao.HeroDao
import practice.effective.chooseyourhero.models.Hero

@Database(entities = [Hero::class], version = 1)
abstract class ChooseYourHeroAppDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
}
