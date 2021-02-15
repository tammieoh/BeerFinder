package com.example.homework2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private List<Beer> beers, favoriteBeers;
    private String selected;

    public BeerAdapter(List<Beer> beers) {
        this.beers = beers;
        this.favoriteBeers = new ArrayList<>();
    }


    @NonNull
    @Override
    public BeerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View beerView = inflater.inflate(R.layout.item_beers, parent, false);
        ViewHolder viewHolder = new ViewHolder(beerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BeerAdapter.ViewHolder holder, int position) {
        Beer beer = beers.get(position);
        holder.textView_beerName.setText(beer.getName());
        holder.textView_beerDesc.setText(beer.getDescription());
//        holder.imageView_beerImage.setText(beer.getImage_url());
        Picasso.get().load(beer.getImage_url()).into(holder.imageView_beerImage);

        String favorite_file = "file:///android_asset/images/favorite.png";
        String unfavorite_file = "file:///android_asset/images/unfavorite.png";
        if (favoriteBeers.contains(beer)) {
            Picasso.get().load(favorite_file).into(holder.imageView_favorite);
        } else {
            Picasso.get().load(unfavorite_file).into(holder.imageView_favorite);
        }
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView_beerName;
        TextView textView_beerDesc;
        ImageView imageView_beerImage, imageView_favorite, imageView_unfavorite;


        public ViewHolder(View itemView) {
            super(itemView);
            textView_beerName = itemView.findViewById(R.id.beer_title);
            textView_beerDesc = itemView.findViewById(R.id.beer_desc);
            imageView_beerImage = itemView.findViewById(R.id.beer_imageView);
            imageView_favorite = itemView.findViewById(R.id.imageView_favorite);
            imageView_beerImage.setOnClickListener(this);
            imageView_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int selected = getAdapterPosition();
                    Beer selectedBeer = beers.get(selected);
                    Log.d("clicked", Integer.toString(selected));
                    // create an intent and send it to the next activity
                    if (favoriteBeers.contains(selectedBeer)) {
                        favoriteBeers.remove(selectedBeer);
                    } else {
                        favoriteBeers.add(selectedBeer);
                    }
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onClick(View view) {
            int selected = getAdapterPosition();
            Beer selectedBeer = beers.get(selected);
            Intent intent = new Intent(view.getContext(), FourthActivity.class);
            intent.putExtra("beer", selectedBeer.getName());
            view.getContext().startActivity(intent);

        }

    }
}
