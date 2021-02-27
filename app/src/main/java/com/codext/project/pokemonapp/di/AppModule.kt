package com.codext.project.pokemonapp.di

import com.codext.project.core.domain.usecase.PokemonInteractor
import com.codext.project.core.domain.usecase.PokemonUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun providePokemonUseCase(pokemonInteractor: PokemonInteractor): PokemonUseCase

}
