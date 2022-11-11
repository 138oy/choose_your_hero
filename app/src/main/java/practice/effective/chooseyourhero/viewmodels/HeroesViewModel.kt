package practice.effective.chooseyourhero.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import practice.effective.chooseyourhero.models.Hero
import practice.effective.chooseyourhero.network.dtos.HeroDto
import practice.effective.chooseyourhero.repositories.HeroesRepository
import practice.effective.chooseyourhero.ui.HeroUiState

class HeroesViewModel(private val repository: HeroesRepository = HeroesRepository()) : ViewModel() {
    private val _state = MutableStateFlow<HeroUiState>(HeroUiState.Empty)
    val state = _state.asStateFlow()

    internal fun getHeroesList() {
        viewModelScope.launch {
            val res = repository.getHeroesList()
                .map { list ->
                    list.map { elem -> mapDtoToEntity(elem) }
                }
            _state.value = HeroUiState.HeroesData(res.single().toMutableStateList())
        }
    }

    internal fun getHero(id: String) {
        viewModelScope.launch {
            val res = repository.getHero(id)
                .map { elem -> mapDtoToEntity(elem) }
            _state.value = HeroUiState.HeroesData(mutableListOf(res.single()))
        }
    }

    private fun mapDtoToEntity(dto: HeroDto): Hero {
        return (
                Hero(
                    id = dto.id.toString(),
                    name = dto.name,
                    description = if (dto.description != "") dto.description else "Hi! I'm ${dto.name}",
                    imageUrl = dto.thumbnail.path.replace("http:", "https:") +
                            "/portrait_uncanny." +
                            dto.thumbnail.extension
                )
                )
    }
}
