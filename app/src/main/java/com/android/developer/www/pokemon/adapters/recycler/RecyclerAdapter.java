package com.android.developer.www.pokemon.adapters.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.developer.www.pokemon.ActivityCard;
import com.android.developer.www.pokemon.R;
import com.android.developer.www.pokemon.data.Pokemon;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    public static final String KEY = "com.android.developer.www.pokemon.adapters.recycler.id";

    private Context context;

    private List<Pokemon> pokemons = new ArrayList<>();

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons.addAll(pokemons);
    }

    public void clearData() {
        pokemons.clear();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Pokemon pokemon = pokemons.get(position);
        holder.tv_name.setText(pokemon.getName());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityCard.class);
                intent.putExtra(KEY, pokemon.getNumber());
                context.startActivity(intent);
            }
        });

        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/" + pokemon.getNumber() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv_photo);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_recycler_item) LinearLayout item;
        @BindView(R.id.iv_pokemon_photo_recycler_item) ImageView iv_photo;
        @BindView(R.id.tv_pokemon_name_recycler_item) TextView tv_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
