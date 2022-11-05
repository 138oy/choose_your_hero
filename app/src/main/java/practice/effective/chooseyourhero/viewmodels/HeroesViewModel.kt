package practice.effective.chooseyourhero.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
        viewModelScope.launch {
            runBlocking {
                val res = repository.getHero(id)
                res.collect { elem -> hero.add(mapDtoToEntity(elem)) }
            }
        }
        return hero.single()
    }

    private fun mapDtoToEntity(dto: HeroDto): Hero {
        return (
                Hero(
                    id = dto.id.toString(),
                    name = dto.name,
                    description = if (dto.description != "") dto.description else "Hi! I'm ${dto.name}",
                    imageUrl = dto.thumbnail.path.replace("http", "https") +
                            "/portrait_uncanny." +
                            dto.thumbnail.extension
                )
                )
    }
}
