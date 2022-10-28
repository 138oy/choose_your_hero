package practice.effective.chooseyourhero.viewmodels

import androidx.lifecycle.ViewModel
import practice.effective.chooseyourhero.models.Hero
import practice.effective.chooseyourhero.repositories.HeroesRepository

class HeroesViewModel(repository: HeroesRepository = HeroesRepository()) : ViewModel() {
    private val heroesList: List<Hero> = repository.getHeroesList()

    internal fun getHeroesList() = heroesList
}
