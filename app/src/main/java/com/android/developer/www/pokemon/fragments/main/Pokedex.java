package com.android.developer.www.pokemon.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.developer.www.pokemon.R;
import com.android.developer.www.pokemon.adapters.recycler.RecyclerAdapter;
import com.android.developer.www.pokemon.data.Pokemon;
import com.android.developer.www.pokemon.database.DBHelper;
import com.android.developer.www.pokemon.models.ModelPokedex;
import com.android.developer.www.pokemon.presenters.PresenterPokedex;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Pokedex extends Fragment {

    private PresenterPokedex presenter;
    private RecyclerAdapter adapter;

    @BindView(R.id.recycler_pokemon_list_layout) RecyclerView recycler;
    @BindView(R.id.tv_not_data_pokemon_list_layout) TextView tv_not_data;
    @BindView(R.id.progress_pokemon_list_layout) ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pokemon_list_layout, container, false);
        ButterKnife.bind(this, v);

        presenter = new PresenterPokedex(new ModelPokedex(DBHelper.getInstance(getContext())));
        presenter.attachView(this);

        adapter = new RecyclerAdapter(getContext());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("---My Log---", "onResumePokedex");
        presenter.viewReady();
    }

    public void showList(List<Pokemon> pokemons) {
        progressBar.setVisibility(View.GONE);
        tv_not_data.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
        adapter.clearData();
        adapter.setPokemons(pokemons);
        adapter.notifyDataSetChanged();
    }

    public void showEmptyList() {
        progressBar.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        tv_not_data.setVisibility(View.VISIBLE);
        tv_not_data.setText("В покедексе пока нет покемонов");
    }

    public void showLoad() {
        recycler.setVisibility(View.GONE);
        tv_not_data.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        Log.d("---My Log---", "onDestroy Pokedex");
    }
}
