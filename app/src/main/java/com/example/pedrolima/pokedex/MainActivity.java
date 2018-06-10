package com.example.pedrolima.pokedex;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pedrolima.pokedex.model.Pokemon;
import com.example.pedrolima.pokedex.model.PokemonResposta;
import com.example.pedrolima.pokedex.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private  static final String TAG = "POKEDEX";

    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obterdados();

    }

    private void obterdados() {
        PokeapiService service = retrofit.create(PokeapiService.class);
        final Call<PokemonResposta> pokemonRespostaCall = service.obterListaPokenon();

                pokemonRespostaCall.enqueue(new Callback<PokemonResposta>() {
            @Override
            public void onResponse(Call<PokemonResposta> call, Response<PokemonResposta> response) {

                if (response.isSuccessful()){

                        PokemonResposta pokemonResposta = response.body();
                        ArrayList<Pokemon> listaPokemon = pokemonResposta.getResults();

                    for (int i = 0; i < listaPokemon.size(); i++ ) {
                        Pokemon p = listaPokemon.get(i);
                        Log.i(TAG, "Pokemon: " +p.getName());

                    }
                } else{
                    Log.e(TAG,"onResponse" + response.errorBody());

                }

            }

            @Override
            public void onFailure(Call<PokemonResposta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());

            }
        });

    }
}
