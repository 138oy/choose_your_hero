package practice.effective.chooseyourhero.network

import practice.effective.chooseyourhero.BuildConfig
import practice.effective.chooseyourhero.network.dtos.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val apikey = BuildConfig.PUBLIC_KEY
private val hash = BuildConfig.HASH

interface MarvelApiService {
    @GET("/v1/public/characters")
    suspend fun getAllHeroes(
        @Query("limit") limit: Int = 10,
        @Query("ts") ts: Int = 1,
        @Query("apikey") key: String = apikey,
        @Query("hash") hashKey: String = hash,
    ): ResponseDto

    @GET("/v1/public/characters/{characterId}")
    suspend fun getHeroById(
        @Path("characterId") id: String,
        @Query("ts") ts: Int = 1,
        @Query("apikey") key: String = apikey,
        @Query("hash") hashKey: String = hash,
    ): ResponseDto
}
