package com.android.developer.www.pokemon.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.developer.www.pokemon.R;
import com.android.developer.www.pokemon.adapters.recycler.RecyclerAdapter;
import com.android.developer.www.pokemon.data.Pokemon;
import com.android.developer.www.pokemon.models.ModelDiscover;
import com.android.developer.www.pokemon.presenters.PresenterDiscover;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Discover extends Fragment {

    private PresenterDiscover presenter;
    private RecyclerAdapter adapter;
    private GridLayoutManager layoutManager;

    @BindView(R.id.recycler_pokemon_list_main_layout) RecyclerView recycler;
    @BindView(R.id.tv_not_data_pokemon_list_layout) TextView tv_not_data;
    @BindView(R.id.progress_pokemon_list_layout) ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pokemon_list_layout, container, false);
        ButterKnife.bind(this, v);

        presenter = new PresenterDiscover(new ModelDiscover());
        presenter.attachView(this);

        adapter = new RecyclerAdapter(getContext());
        recycler.setAdapter(adapter);
        layoutManager = new GridLayoutManager(getContext(), 3);
        recycler.setLayoutManager(layoutManager);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                presenter.onScroll(dy);
            }
        });
        presenter.viewReady();
        return v;
    }

    public void viewIsReady() {
        presenter.viewReady();
    }

    public void showList(List<Pokemon> pokemons) {
        progressBar.setVisibility(View.GONE);
        tv_not_data.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
        adapter.setPokemons(pokemons);
        adapter.notifyDataSetChanged();
    }

    public void showError() {
        progressBar.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        tv_not_data.setVisibility(View.VISIBLE);
        tv_not_data.setText("Ошибка. Что-то пошло не так");
    }

    public void showLoad() {
        recycler.setVisibility(View.GONE);
        tv_not_data.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public int getVisibleItems() {
        return layoutManager.getChildCount();
    }

    public int getTotalItems() {
        return layoutManager.getItemCount();
    }

    public int getPastItems() {
        return layoutManager.findFirstVisibleItemPosition();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}
