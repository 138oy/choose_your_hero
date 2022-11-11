package practice.effective.chooseyourhero.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import practice.effective.chooseyourhero.network.MarvelApi
import practice.effective.chooseyourhero.network.dtos.HeroDto

class HeroesRepository(private val api: MarvelApi = MarvelApi) {
    internal fun getHeroesList(): Flow<List<HeroDto>> {
        val res: Flow<List<HeroDto>> = flow {
            val response = api.retrofitService.getAllHeroes().data.results
            emit(response)
        }
        return res
    }

    internal fun getHero(id: String): Flow<HeroDto> {
        val res: Flow<HeroDto> = flow {
            val response = api.retrofitService.getHeroById(id).data.results.single()
            emit(response)
        }
        return res
    }
}
