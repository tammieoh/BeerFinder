package com.example.homework2;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private List<Beer> beers, favoriteBeers;
    private Beer selectedBeer;
    private String beer_name, beer_img, beer_abv, beer_desc, beer_brew, beer_tips;;
    private ArrayList<String> beer_foodPairs = new ArrayList<>();
    private String base_url = "https://api.punkapi.com/v2/beers?";
    private static AsyncHttpClient client = new AsyncHttpClient();

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

    public void updateList(List<Beer> list){
        beers = list;
        notifyDataSetChanged();
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
            System.out.println(selectedBeer);
            intent.putExtra("beer", selectedBeer.getName());
            view.getContext().startActivity(intent);
        }
    }

}

//                client.addHeader("Accept", "application/json");
//                // send a get request to the api url
//                client.get(base_url, new AsyncHttpResponseHandler() {
//
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                        Intent intent = new Intent(view.getContext(), FourthActivity.class);
//                        JSONArray json = null;
//                        try {
//                            json = new JSONArray(new String(responseBody));
//                            beer_name = json.getJSONObject(0).getString("name");
//                            beer_img = json.getJSONObject(0).getString("image_url");
//                            beer_abv = json.getJSONObject(0).getString("abv");
//                            beer_brew = json.getJSONObject(0).getString("first_brewed");
//                            beer_desc = json.getJSONObject(0).getString("description");
//                            beer_tips = json.getJSONObject(0).getString("brewers_tips");
//                            for(int i = 0; i < json.getJSONObject(0).getJSONArray("food_pairing").length(); i++) {
//                                beer_foodPairs.add(json.getJSONObject(0).getJSONArray("food_pairing").get(i).toString());
//                            }
//                            Beer selectedBeer = new Beer(beer_name ,beer_desc, beer_img, beer_abv, beer_brew, beer_foodPairs, beer_tips);
//                            intent.putExtra("beer_img", beer_img);
//                            intent.putExtra("beer_obj", (Parcelable) selectedBeer);
//            intent.putExtra("beer_abv", beer_abv);
//            intent.putExtra("beer_desc", beer_desc);
//            intent.putExtra("beer_brew", beer_brew);
//            intent.putExtra("beer_tips", beer_tips);
//            intent.putStringArrayListExtra("beer_FoodPairs", beer_foodPairs);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                        Log.d("error with url", base_url);
//                    }
//                });
//            }
//            intent.putExtra("beer_img", beer_img);
//            intent.putExtra("beer_abv", beer_abv);
//            intent.putExtra("beer_desc", beer_desc);
//            intent.putExtra("beer_brew", beer_brew);
//            intent.putExtra("beer_tips", beer_tips);
//            intent.putStringArrayListExtra("beer_FoodPairs", beer_foodPairs);




//        public void launchNextActivity(String beer_name) {
//            Intent intent = new Intent(view.getContext(), FourthActivity.class);
//            System.out.println(selectedBeer);
//            intent.putExtra("beer", selectedBeer.getName());
//            view.getContext().startActivity(intent);
//            // set the header because of the api endpoint
//            base_url += "beer_name=" + beer_name;
//            client.addHeader("Accept", "application/json");
//            // send a get request to the api url
//            client.get(base_url, new AsyncHttpResponseHandler() {
//
////                beer_img, beer_abv, beer_desc, beer_brew, beer_foodPair, beer_tips;;
//
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//                    JSONArray json = null;
//                    try {
//                        json = new JSONArray(new String(responseBody));
//                        beer_img = json.getJSONObject(0).getString("image_url");
//                        beer_abv = json.getJSONObject(0).getString("abv");
//                        beer_brew = json.getJSONObject(0).getString("first_brewed");
//                        beer_desc = json.getJSONObject(0).getString("description");
//                        beer_tips = json.getJSONObject(0).getString("brewers_tips");
//                        for(int i = 0; i < json.getJSONObject(0).getJSONArray("food_pairing").length(); i++) {
//                            beer_foodPairs.add(json.getJSONObject(0).getJSONArray("food_pairing").get(i).toString());
//                        }
//                        selectedBeer = new Beer(beer_name ,beer_desc, beer_img, beer_abv, beer_brew, beer_foodPairs, beer_tips);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                @Override
//                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                    Log.d("error with url", base_url);
//                }
//            });
////                    ArrayList<String> names_array = new ArrayList<>();
////                    ArrayList<String> desc_array = new ArrayList<>();
////                    ArrayList<String> images_array = new ArrayList<>();
////
////                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
////                    for(int i = 0; i < json.length(); i++) {
////                        names_array.add(json.getJSONObject(i).getString("name"));
////                        desc_array.add(json.getJSONObject(i).getString("description"));
////                        images_array.add(json.getJSONObject(i).getString("image_url"));
////                    }
////                    intent.putExtra("names", names_array);
////                    intent.putExtra("descs", desc_array);
////                    intent.putExtra("images", images_array);
//
//                }
//                @Override
//                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                    Log.d("error with url", base_url);
//                }
//            });
//
//}
