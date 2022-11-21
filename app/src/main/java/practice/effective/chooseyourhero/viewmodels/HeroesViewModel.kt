package practice.effective.chooseyourhero.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import practice.effective.chooseyourhero.models.Hero
import practice.effective.chooseyourhero.navigation.ErrorMessage
import practice.effective.chooseyourhero.network.ResponseWrapper
import practice.effective.chooseyourhero.network.dtos.HeroDto
import practice.effective.chooseyourhero.repositories.HeroesRepository
import practice.effective.chooseyourhero.ui.HeroUiState

@HiltViewModel
class HeroesViewModel @Inject constructor(private val repository: HeroesRepository) : ViewModel() {
    private val _state = MutableStateFlow<HeroUiState>(HeroUiState.Empty)
    val state = _state.asStateFlow()

    internal fun getHeroesList(navController: NavController) {
        viewModelScope.launch {
            when (val res = repository.getHeroesList().single()) {

                is ResponseWrapper.NetworkError -> try {
                    val list: MutableList<Hero> = mutableStateListOf()
                    val dbRes = repository.getHeroesListCached()
                    dbRes.single().forEach { elem -> list.add(elem) }
                    _state.value = HeroUiState.HeroesData(list)
                    Log.d("heroes", "$list")
                } catch (e: Throwable) {
                    Log.d("network error!", "${e.message}")
                    navController.navigate(ErrorMessage.route)
                }

                is ResponseWrapper.GenericError -> try {
                    val list: MutableList<Hero> = mutableStateListOf()
                    val dbRes = repository.getHeroesListCached()
                    dbRes.single().forEach { elem -> list.add(elem) }
                    _state.value = HeroUiState.HeroesData(list)
                } catch (e: Throwable) {
                    Log.d("generic error!", "${e.message}")
                    navController.navigate(ErrorMessage.route)
                }

                is ResponseWrapper.Success -> {
                    val list: MutableList<Hero> = mutableStateListOf()
                    res.value.forEach { elem -> list.add(mapDtoToEntity(elem)) }
                    repository.insertHeroesList(list)
                    _state.value = HeroUiState.HeroesData(list)
                }
            }
        }
    }

    internal fun getHero(id: String, navController: NavController) {
        viewModelScope.launch {
            when (val res = repository.getHero(id).single()) {

                is ResponseWrapper.NetworkError -> try {
                    val list: MutableList<Hero> = mutableStateListOf()
                    val dbRes = repository.getHeroCached(id)
                    list.add(dbRes.single())
                    _state.value = HeroUiState.HeroesData(list)
                    Log.d("heroes", "$list")
                } catch (e: Throwable) {
                    Log.d("network error!", "${e.message}")
                    navController.navigate(ErrorMessage.route)
                }

                is ResponseWrapper.GenericError -> try {
                    val list: MutableList<Hero> = mutableStateListOf()
                    val dbRes = repository.getHeroCached(id)
                    list.add(dbRes.single())
                    _state.value = HeroUiState.HeroesData(list)
                    Log.d("heroes", "$list")
                } catch (e: Throwable) {
                    Log.d("generic error!", "${e.message}")
                    navController.navigate(ErrorMessage.route)
                }

                is ResponseWrapper.Success -> {
                    val list: MutableList<Hero> = mutableStateListOf()
                    list.add(mapDtoToEntity(res.value))
                    _state.value = HeroUiState.HeroesData(list)
                }
            }
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
