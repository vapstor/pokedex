package br.com.vapstor.pokedex.di

import br.com.vapstor.pokedex.data.remote.PokemonAPI
import br.com.vapstor.pokedex.data.repository.PokemonRepoImpl
import br.com.vapstor.pokedex.domain.repository.PokemonRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

/**
 *  Módulo para injeção de dependência
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonModule {
    @Binds
    abstract fun bindPokemonRepo(
        impl: PokemonRepoImpl,
    ): PokemonRepo
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val json = Json {
        ignoreUnknownKeys = true
    }

    val contentType = "application/json".toMediaType()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    @Provides
    @Singleton
    fun providePokemonApi(retrofit: Retrofit): PokemonAPI = retrofit.create(PokemonAPI::class.java)
}

const val BASE_URL = "https://pokeapi.co/api/v2/"
