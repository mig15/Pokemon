package com.android.developer.www.pokemon;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.developer.www.pokemon.adapters.recycler.RecyclerAdapter;
import com.android.developer.www.pokemon.models.ModelCard;
import com.android.developer.www.pokemon.presenters.PresenterCard;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityCard extends AppCompatActivity {

    private PresenterCard presenter;

    @BindView(R.id.iv_pokemon_photo_activity_card) ImageView iv_photo;
    @BindView(R.id.tv_name_activity_card) TextView tv_name;
    @BindView(R.id.iv_database_activity_card) ImageView iv_db;
    @BindView(R.id.tv_characteristics_activity_card) TextView tv_characteristics;
    @BindView(R.id.tv_spells_activity_card) TextView tv_spells;

    @BindDrawable(R.drawable.ic_add) Drawable save;
    @BindDrawable(R.drawable.ic_delete) Drawable delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_card);
        ButterKnife.bind(this);

        presenter = new PresenterCard(new ModelCard());
        presenter.attachView(this);
        presenter.viewReady();

        Glide.with(this)
                .load("http://pokeapi.co/media/sprites/pokemon/" + getPokemonId() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_photo);

        //TODO показывать только после того как получен ответ.
    }

    public void setName(String name) {
        tv_name.setText(name);
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public int getPokemonId() {
        return getIntent().getIntExtra(RecyclerAdapter.KEY, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
