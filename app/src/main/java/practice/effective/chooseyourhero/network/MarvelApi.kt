package practice.effective.chooseyourhero.network

object MarvelApi {
    val retrofitService: MarvelApiService by lazy {
        retrofit.create(MarvelApiService::class.java)
    }
}