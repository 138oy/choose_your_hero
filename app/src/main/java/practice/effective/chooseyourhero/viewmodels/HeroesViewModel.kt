package practice.effective.chooseyourhero.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import practice.effective.chooseyourhero.models.Hero
import practice.effective.chooseyourhero.network.dtos.HeroDto
import practice.effective.chooseyourhero.repositories.HeroesRepository

const val DELAY: Long = 300

class HeroesViewModel(private val repository: HeroesRepository = HeroesRepository()) : ViewModel() {
    private val _singleHero = MutableStateFlow<Flow<Hero>>(flow{emit(Hero("", "", "", ""))})
    val singleHero = _singleHero.asSharedFlow()

    internal fun getHeroesList(): List<Hero> {
        val heroesList: MutableList<Hero> = mutableStateListOf()
        viewModelScope.launch {
            val res = repository.getHeroesList()
            res.collect { elem -> elem.forEach { dto -> heroesList.add(mapDtoToEntity(dto)) } }
        }
        return heroesList
    }

    @OptIn(FlowPreview::class)
    internal fun getHero(id: String) {
        val res = repository.getHero(id).debounce(DELAY).distinctUntilChanged()
            .map { elem -> mapDtoToEntity(elem) }
        _singleHero.update { res }

//        val hero: MutableList<Hero> = mutableStateListOf()
//        viewModelScope.launch {
//            runBlocking {

//                val res = repository.getHero(id)
//                res.collect { elem -> hero.add(mapDtoToEntity(elem)) }
//            }
//        }
//        return hero.single()
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
