package com.codext.project.core.di

import com.codext.project.core.data.source.PokemonRepository
import com.codext.project.core.domain.repository.IPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [DatabaseModule::class, NetworkModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(pokemonRepository: PokemonRepository): IPokemonRepository

}