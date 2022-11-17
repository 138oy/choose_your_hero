package practice.effective.chooseyourhero.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import practice.effective.chooseyourhero.database.ChooseYourHeroAppDatabase
import practice.effective.chooseyourhero.database.dao.HeroDao
import practice.effective.chooseyourhero.network.MarvelApiService
import practice.effective.chooseyourhero.repositories.HeroesRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ChooseYourHeroAppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ChooseYourHeroAppDatabase::class.java,
            "hero_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHeroDao(db: ChooseYourHeroAppDatabase) = db.heroDao()

    @Provides
    @Singleton
    fun provideHeroRepository(
        dao: HeroDao,
        api: MarvelApiService
    ): HeroesRepository = HeroesRepository(dao, api)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://gateway.marvel.com")
            .build()
    }

    @Provides
    @Singleton
    fun provideMarvelApi(retrofit: Retrofit): MarvelApiService {
        return retrofit.create(MarvelApiService::class.java)
    }
}
