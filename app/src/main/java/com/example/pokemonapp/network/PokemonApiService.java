package com.example.pokemonapp.network;

import com.example.pokemonapp.models.PokemonResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PokemonApiService {

    @GET("pokemon")
    Observable<PokemonResponse> getPokemons();
}
