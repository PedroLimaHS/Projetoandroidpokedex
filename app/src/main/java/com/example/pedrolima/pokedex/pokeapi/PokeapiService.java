package com.example.pedrolima.pokedex.pokeapi;

import com.example.pedrolima.pokedex.model.PokemonResposta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeapiService {

    @GET("Pokemon")
    Call<PokemonResposta> obterListaPokenon();
}
