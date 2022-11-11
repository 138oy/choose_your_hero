package practice.effective.chooseyourhero.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import practice.effective.chooseyourhero.models.Hero

data class HeroUiState(
    val isFetched: Boolean = false,
    val heroesFlow: Flow<Hero> = emptyFlow(),
) {
    companion object {
        val Empty = HeroUiState()
    }
}
