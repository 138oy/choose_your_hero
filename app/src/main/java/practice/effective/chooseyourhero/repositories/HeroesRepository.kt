package practice.effective.chooseyourhero.repositories

import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import practice.effective.chooseyourhero.database.dao.HeroDao
//import practice.effective.chooseyourhero.network.MarvelApi
import practice.effective.chooseyourhero.network.MarvelApiService
import practice.effective.chooseyourhero.network.ResponseWrapper
import practice.effective.chooseyourhero.network.dtos.HeroDto
import practice.effective.chooseyourhero.network.safeApiCall

class HeroesRepository @Inject constructor(
    private val dao: HeroDao,
    private val api: MarvelApiService
) {
    internal fun getHeroesList(): Flow<ResponseWrapper<List<HeroDto>>> {
        val res: Flow<ResponseWrapper<List<HeroDto>>> = flow {
            val response =
                safeApiCall(Dispatchers.IO) { api.getAllHeroes().data.results }
            emit(response)
        }
        return res
    }

    internal fun getHero(id: String): Flow<ResponseWrapper<HeroDto>> {
        val res: Flow<ResponseWrapper<HeroDto>> = flow {
            val response =
                safeApiCall(Dispatchers.IO) { api.getHeroById(id).data.results.single() }
            emit(response)
        }
        return res
    }
}
