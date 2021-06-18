package com.example.pokemonapp.repository;

import com.example.pokemonapp.models.PokemonResponse;
import com.example.pokemonapp.network.PokemonApiService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {
    private PokemonApiService pokemonApiService;

    @Inject
    public Repository(PokemonApiService pokemonApiService) {
        this.pokemonApiService = pokemonApiService;
    }

    public Observable<PokemonResponse> getPokemons() {
        return pokemonApiService.getPokemons();
    }
}
