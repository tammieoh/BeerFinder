package com.example.homework2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    private TextView beer_name, beer_desc, total_results;
    private ImageView beer_image, favorite_icon, unfavorite_icon;
    private RecyclerView recyclerView;
    private ArrayList<String> beer_names, beer_descs, beer_images;
    private ArrayList<Beer> beers;

    @SuppressLint("StringFormatMatches")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        recyclerView = findViewById(R.id.recyclerView);
        beers = new ArrayList<>();

        beer_name = findViewById(R.id.beer_title);
        beer_desc = findViewById(R.id.beer_desc);
        beer_image = findViewById(R.id.beer_imageView);
        total_results = findViewById(R.id.results_textView);

        Intent intent = getIntent();
        beer_names = intent.getStringArrayListExtra("names");
        beer_descs = intent.getStringArrayListExtra("descs");
        beer_images = intent.getStringArrayListExtra("images");

        for(int i = 0; i < beer_names.size(); i++) {
            Beer beer = new Beer(beer_names.get(i),
                    beer_descs.get(i),
                    beer_images.get(i));
            beers.add(beer);
        }

        // create a beer adapter
        BeerAdapter adapter = new BeerAdapter(beers);
        System.out.println(adapter.getItemCount());
        //attach the adapter to the recyclerview
        recyclerView.setAdapter(adapter);

        // set layoutmanager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        total_results.setText(getString(R.string.results, Integer.toString(adapter.getItemCount())));
    }
}
