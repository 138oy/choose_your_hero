package practice.effective.chooseyourhero.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import practice.effective.chooseyourhero.BuildConfig
import practice.effective.chooseyourhero.network.dtos.ResponseDto
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://gateway.marvel.com"
private val apikey = BuildConfig.PUBLIC_KEY
private val hash = BuildConfig.HASH

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

internal val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

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
