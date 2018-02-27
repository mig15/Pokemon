package com.android.developer.www.pokemon.presenters;

import android.util.Log;

import com.android.developer.www.pokemon.fragments.main.Discover;
import com.android.developer.www.pokemon.data.Pokemon;
import com.android.developer.www.pokemon.data.PokemonRepository;
import com.android.developer.www.pokemon.models.ModelDiscover;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterDiscover {

    private Discover view;
    private ModelDiscover model;

    private int offset = 0;
    private boolean wasResponse;

    public PresenterDiscover(ModelDiscover model) {
        this.model = model;
    }

    public void attachView(Discover view) {
        this.view = view;
    }

    public void viewReady() {
        sendRequest();
        view.showLoad();
    }

    public void detachView() {
        view = null;
    }

    private void sendRequest() {
        Call<PokemonRepository> respuestaCall = model.getRequest(offset);
        respuestaCall.enqueue(new Callback<PokemonRepository>() {
            @Override
            public void onResponse(Call<PokemonRepository> call, Response<PokemonRepository> response) {
                wasResponse = true;

                if (!response.isSuccessful()) {
                    wasResponse = true;
                    view.showError();
                    return;
                }

                PokemonRepository rep = response.body();
                List<Pokemon> pokemons = rep.getResults();

                if (pokemons != null && !pokemons.isEmpty()) {
                    view.showList(pokemons);
                }
            }

            @Override
            public void onFailure(Call<PokemonRepository> call, Throwable t) {
                Log.d("---My Log---", "onFailure");
                wasResponse = true;
                view.showError();
            }
        });
    }

    public void onScroll(int dy) {
        //Если recycler листают вниз
        if (dy > 0) {
            int visibleItemCount = view.getVisibleItems();
            int totalItemCount = view.getTotalItems();
            int pastVisibleItems = view.getPastItems();

            //Дожидаемся ответа если был запрос на предыдущем пролистывании
            if (wasResponse) {
                //Если на экране уже видны все элементы
                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    wasResponse = false;
                    offset += 20;
                    sendRequest();
                }
            }
        }
    }
}
