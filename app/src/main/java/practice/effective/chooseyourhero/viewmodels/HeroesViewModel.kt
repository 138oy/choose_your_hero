package practice.effective.chooseyourhero.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import practice.effective.chooseyourhero.models.Hero
import practice.effective.chooseyourhero.network.dtos.HeroDto
import practice.effective.chooseyourhero.repositories.HeroesRepository

class HeroesViewModel(private val repository: HeroesRepository = HeroesRepository()) : ViewModel() {

    internal fun getHeroesList(): List<Hero> {
        val heroesList: MutableList<Hero> = mutableStateListOf()
        viewModelScope.launch {
            val res = repository.getHeroesList()
            res.collect { elem -> elem.forEach { dto -> heroesList.add(mapDtoToEntity(dto)) } }
        }
        return heroesList
    }

    internal fun getHero(id: String): Hero {
        val hero: MutableList<Hero> = mutableStateListOf()
        Log.d("trying: ", "to get hero")
        viewModelScope.launch {
            val res = repository.getHero(id)
            Log.d("got: ", res.first().name)
            res.collect { elem -> hero.add(mapDtoToEntity(elem)) }
        }
        Log.d("success: ", repository.getHero(id).toString())
        return hero.first()
    }

    private fun mapDtoToEntity(dto: HeroDto): Hero {
        return (
                Hero(
                    id = dto.id.toString(),
                    name = dto.name,
                    description = dto.description?: "Hi! I'm ${dto.name}",
                    imageUrl = dto.thumbnail.path.replace("http", "https") +
                            "/portrait_uncanny." +
                            dto.thumbnail.extension
                )
                )
    }
}
