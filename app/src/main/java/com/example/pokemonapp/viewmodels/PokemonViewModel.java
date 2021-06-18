package com.example.pokemonapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemonapp.models.Pokemon;
import com.example.pokemonapp.models.PokemonResponse;
import com.example.pokemonapp.repository.Repository;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


@HiltViewModel
public class PokemonViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<ArrayList<Pokemon>> pokemonList = new MutableLiveData<>();

    @Inject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    public void getPokemons() {
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map((Function<PokemonResponse, ArrayList<Pokemon>>) pokemonResponse -> {
                    ArrayList<Pokemon> list = pokemonResponse.getResults();
                    for (Pokemon pokemon: list) {
                        String url = pokemon.getUrl();
                        String[] pokemonIndex = url.split("/");
                        pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                    }
                    return list;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> pokemonList.setValue(result),
                        error -> Log.e("ViewModel",error.getMessage())
                        );
    }


}
