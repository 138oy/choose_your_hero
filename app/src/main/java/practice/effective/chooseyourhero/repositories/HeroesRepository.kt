package practice.effective.chooseyourhero.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import practice.effective.chooseyourhero.network.MarvelApi
import practice.effective.chooseyourhero.network.ResponseWrapper
import practice.effective.chooseyourhero.network.dtos.HeroDto
import practice.effective.chooseyourhero.network.safeApiCall

class HeroesRepository(private val api: MarvelApi = MarvelApi) {
    internal fun getHeroesList(): Flow<ResponseWrapper<List<HeroDto>>> {
        val res: Flow<ResponseWrapper<List<HeroDto>>> = flow {
            val response =
                safeApiCall(Dispatchers.IO) { api.retrofitService.getAllHeroes().data.results }
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
