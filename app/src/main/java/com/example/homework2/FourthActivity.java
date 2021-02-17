package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class FourthActivity extends AppCompatActivity {
    private TextView beerLabel, beer_abv, beer_desc, beer_brew, brew_tips, beer_foodPairs;
    private ImageView beer_img;
    private String beer_name, foodPairs;
    private String base_url = "https://api.punkapi.com/v2/beers?";
    private static AsyncHttpClient client = new AsyncHttpClient();
    private Button home_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        beerLabel = findViewById(R.id.beerLabel_TextView);
        beer_img = findViewById(R.id.singleBeer_imageView);
        beer_abv = findViewById(R.id.abv_TextView);
        beer_desc = findViewById(R.id.singleDesc_TextView);
        beer_brew = findViewById(R.id.brewDate_TextView);
        brew_tips = findViewById(R.id.brewTips_TextView);
        beer_foodPairs = findViewById(R.id.foodPair_TextView);

        home_button = findViewById(R.id.home_button);

        foodPairs = "";
        Intent intent = getIntent();
        beer_name = intent.getStringExtra("beer");
        beerLabel.setText(beer_name);

        base_url += "beer_name=" + beer_name;
        client.addHeader("Accept", "application/json");
        // send a get request to the api url
        client.get(base_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONArray json = null;
                try {
                    json = new JSONArray(new String(responseBody));
                    beerLabel.setText(json.getJSONObject(0).getString("name"));
//                    beer_img.setText(json.getJSONObject(0).getString("image_url"));
//                    beer_img = json.getJSONObject(0).getString("image_url");
                    Picasso.get().load(json.getJSONObject(0).getString("image_url")).into(beer_img);
                    beer_abv.setText(String.format("ABV: %s", json.getJSONObject(0).getString("abv")));
                    beer_brew.setText(String.format("First Brewed: %s", json.getJSONObject(0).getString("first_brewed")));
                    beer_desc.setText(String.format("Description: %s", json.getJSONObject(0).getString("description")));
                    brew_tips.setText(String.format("Brewer's Tip: %s", json.getJSONObject(0).getString("brewers_tips")));

                    for (int i = 0; i < json.getJSONObject(0).getJSONArray("food_pairing").length(); i++) {
                        if(i == json.getJSONObject(0).getJSONArray("food_pairing").length() - 1) {
                            foodPairs += json.getJSONObject(0).getJSONArray("food_pairing").get(i).toString();
                        }
                        else {
                            foodPairs += json.getJSONObject(0).getJSONArray("food_pairing").get(i).toString() + ", ";
                        }
                    }
                    beer_foodPairs.setText(String.format("Food Pairings: %s", foodPairs));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("error getting params", base_url);
            }
        });
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchNextActivity(view);
            }
        });
//        getInfo(beer_name);
    }
    public void launchNextActivity(View view) {
        Intent intent = new Intent(FourthActivity.this, MainActivity.class);
        startActivity(intent);
    }

//    public String getInfo(String beer_name) {
//        base_url += "beer_name=" + beer_name;
//        // set the header because of the api endpoint
//        client.addHeader("Accept", "application/json");
//        // send a get request to the api url
//        client.get(base_url, new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//
//            }
//        });
//    }
}
