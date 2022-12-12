package practice.effective.chooseyourhero.ui

import practice.effective.chooseyourhero.models.Hero

sealed class HeroUiState {
    object Empty : HeroUiState()
    object Loading : HeroUiState()
    data class HeroesData(val heroesData: MutableList<Hero> = mutableListOf()) : HeroUiState()
}
